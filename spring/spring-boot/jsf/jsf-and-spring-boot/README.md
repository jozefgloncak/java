# About
Following remarks come mainly from tutorial [1](#coreServlets)

Once is project started directly from IDE (IntelliJ) than it is possible it just simply run (e. g. click R-mouse on
 Application class). If you want to crete runnable JAR with embeded Tomcat it is necessary to change packaging in pom to
```xml
<package>war</package>
```
then it is possible to simply run from command line
```
java -jar archive_name.war
```
# JSF
## Lifecycle of JSF
* getter methods of bean are called once form is **displayed**
* setter methods of bean are called once formular is **submitted** 
    * after that, method specified in action attribute of button is called. There can be several action methods if
     there is several buttons on form.

## EL - Expression language
* accesss to concrete element of list ```#{bean.list[index]}```
* accesss to concrete element of array ```#{bean.array[index]}```
* accesss to concrete element of map ```#{bean.map[key]}```
* array notation for accessing properties ```#{bean["mapName"][index]})```
* is string empty (null or '') ```#{empty bean.property}``` - the same is valid also for list, map...

## Predefined variables
* facesContext
* param
* header
* cookie
* request
* initParam
All of this variables are available and can be accessed as #{variableName}. All of them has many getters and access
 many
[useful information](src/main/webapp/collections/collections.xhtml)



## Redirecting
in action define
```
action="{name_of_new_page?faces-redirect=true}"
```
[more](https://stackoverflow.com/questions/15521451/how-to-navigate-in-jsf-how-to-make-url-reflect-current-page-and-not-previous-o)

## Input validation
Following checks are in the same order as they are done in JSF
* check required condition - is mandatory?

```xhtml
<h:inputText id="inputName" value="#{validatorForm.name}" required="true"
             requiredMessage="#{msg1['msg.required']}"/>
```

* check conversion condition - is possible convert input from field to bean attribute type?
```xhtml
<h:inputText id="inputAge" value="#{validatorForm.age}"
             converterMessage="#{msg1['err.convert.toNumber']}"/>
```
It is also possible define own converter inside **inputText** tag
via:
 
```xhtml
<f:converter binding="#{birthNumberConverter}" />
```
in this case **BirthNumberConverter class is annoted with *@FacesConverter("birthNumberConverter")*. This attitude
 make it possible to injecd e.g. dependency on **MessageSource** and return from converter i18n error messsages.
or
```xhtml
<f:converter converterId="birthNumberConverter" />
```
in this case **BirthNumberConverter class is annoted with *@Named @ApplicationScoped*



     
 
* check validation condition - pass through additional validation?
```xhtml
<p:inputText id="inputRating" value="#{validatorForm.rating}"
             validatorMessage="#{msg1['err.range.rating']}">
    <f:validateDoubleRange minimum="0" maximum="5"/>
</p:inputText>
```
or it is possible to define your own validator similarly as converter. Define between **<p:inputText>** tags:
```xhtml
<f:validator validatorId="birthNumberValidator" />
```

## faces-config.xml file
This file has to be situted as follows in main/webpp/WEB-INF/faces-config.xml

File starts like this

```
<?xml version="1.0" encoding="UTF-8"?>
<faces-config xmlns="http://xmlns.jcp.org/xml/ns/javaee"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
        http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd"
              version="2.2">
    <!-- various content -->

</faces-config>
```
Notice that there is version 2.2 but in pom there is version of JSF 2.3. Problem was that this file wasn't possible
 load and I wasn't able to reproduce this [advice](http://alibassam.com/deploying-jsf-2-3-application-tomcat-9/).

### Explicit navigation
It means that you externaly (in file **faces-config.xml**) define what are navigation rules when you click submit
 button on page.
 
You can define (simple example) that if you click submit on page A and result of some procesing is 1 than you will
 navigate to page B, if result is 2 you will navigate to page C and so on.


Structure of file is as follows:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<faces-config xmlns="http://xmlns.jcp.org/xml/ns/javaee"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
        http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd"
              version="2.2">
    <navigation-rule>
        <from-view-id>/calculator.xhtml</from-view-id>
        <navigation-case>
            <from-outcome>5</from-outcome>
            <to-view-id>/calculator-result1.xhtml</to-view-id>
        </navigation-case>
        <navigation-case>
            <from-outcome>6</from-outcome>
            <to-view-id>/calculator-result2.xhtml</to-view-id>
        </navigation-case>
    </navigation-rule>
</faces-config>
``` 
In element ```navigation-rule``` you define subelement ```from-view-id``` where you specify origin page on which is
 button submit. In elements ```navigation-case``` you define result of click on button submit (result of handling
  method - specified in *action* part). Specifically in ```from-outcome``` you define result of handling method and
   in ```to-view-id``` you define page which navigate to.
   
```navigation-rule``` element can be in xml several times.

It is also possible use star (*) or _path/\*_ in ```from-view-id``` what means that this rule will be applied for
 navigation
 from
 whatever page if there is match with ```from-outcome``` value.
 
It is also posible to use **if**. Then outcome can be the same and for diferentiating of next page decide <if> tag.
```xml
<navigation-case>
    <from-outcome>success</from-outcome>
    <if>#{bean.someCheck}</if>
    <to-view-id>/pageA.xhtml</to-view-id>
</navigation-case>
<navigation-case>
    <from-outcome>success</from-outcome>
    <if>#{!bean.someCheck}</if>
    <to-view-id>/pageB.xhtml</to-view-id>
</navigation-case>
```

Finally it is also possible to compute next page also in config as follows:
```xml
<navigation-case>
    <to-view-id>#{bean.methodToResolveNextPage}</to-view-id>
</navigation-case>
```

### Messages
Add to *faces-config.xml* following code.
```xhtml
<application>
    <resource-bundle>
        <base-name>messages</base-name>
        <var>msg</var>
    </resource-bundle>
</application>
```
It describe that file **messages.properties** is placed in *resources* folder. Name of file can be different but then
 it is also necessary change this value in ```<base-name>``` element. Content of file is in *key*=value format e. g.
```properties
label.displayLabels=Should be labels displayed?
label.value1=First value
```
In facelet it is now possible following syntax:
```xhtml
#{msg['label.value1']}
```

### Placeholders
If you want to add to message text some parameter specify messsage in **messages.properties** as follows:
```properties
msg.result=Výsledok je {0} a cislo {1} je prvočíslo.
```

then it is possible use in XHTML
```xhtml
<h:outputFormat value="#{msg['msg.result']}">
    <f:param value="#{expression.result}" />
    <f:param value="#{expression.prime ? '': 'nie'}" />
</h:outputFormat>
```
Parameters values are specified through ```<f:param>``` tag

## XML configuration
Spring bean configuration is possible also through *.xml file. It is necessary specify bean in *.xml file and then
 specify this file in class annoted with **@Configuration** annotation as follows
```java
@Configuration
@ImportResource({"classpath*:beans.xml"})
public class XMLConfiguration {
}
```

In *.xml file it is also necessary to specify scope mainly if bean is used as model to which data are stored from web
 forms
```java
    <bean id="mathService" class="gloncak.jozef.jsp.integratespringjsp2.service.MathematicalServiceImpl" />

    <bean id="expression" class="gloncak.jozef.jsp.integratespringjsp2.model.Expression" scope="session">
        <property name="mathService" ref="mathService" />
    </bean>
```

## Passing arguments to facelet
From JSF 2.2 it possible to call methods of managed bean as follows

```#{expression.analyzePrime()}```

also with parameters



## UI elements
### Conditional displaying
Almost all UI elements have attribute **rendered**. According to value which is set this element is visible or
 invisible. It can be used for conditional displaying of UI elements.
 
 Following *text* is displayed only if ```bean.property``` is empty (null or empty string) otherwise it isn't displayed.
 ```xhtml
<h:outputText value="text" rendered="#{empty bean.property}"/>
```

other possibility is embed HTML code which is conditional to other tag e. g. as follows
```xhtml
<h:panelGroup rendered="#{expression.prime}">
    <h:outputText class="prime" value="This is prime number " />
</h:panelGroup>
```
<span style="{color:#FF0000}">Note that recommended way is through ```ui:fragment``` but this element from some
 version of
 JSF doesn't
 have
 rendered attribute.</span>

### Selection elements
There is many UI elements which can be used for selecting:
* selecting several data items:
    * selectManyCheckbox
    * selectManyListbox
    * selectManyMenu - not vary practical (from list of data display only one item, which is scrollable)
* selecting one dat item
    * selectOneRadio
    * selectOneListbox
    * selectOneMenu
    
For each of previous (should be) option it is possible:
* enumerate options on frontend (value != label)
```xhtml
<h:select{selection_type} id="{id_of_ui_element}" value="#{bean.property}">
    <f:selectItem itemValue="{value1}" itemLabel="{label1}"/>
    <f:selectItem itemValue="{value1}" itemLabel="{label2}"/>
</h:select{selection_type}>
```
* enumerate options on backend (value != label) - in this option it is necessary to have bean with collection (e. g
. list) of available values. In next example it is on backend hidden under *propertyMenu*. In frontend it is
 referenced through variable *menu*. In example *propertyMenu* has 2 properties - name and code.
 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;It is also possible to use as backend collection *Map* than key of map is
  display labels and value is display values).
```xhtml
<h:select{selection_type} id="{id_of_ui_element}" value="#{bean.property}">
    <f:selectItems value="#{bean2.propertyMenu}" var="menu"
                   itemLabel="#{menu.name}"
                   itemValue="#{menu.code}"/>
</h:select{selection_type}>
```
* enumerate options on backend (value == label)
```xhtml
<h:select{selection_type} id="{id_of_ui_element}" value="#{bean.property}">
    <f:selectItems value="#{bean2.propertyMenu}"/>
</h:select{selection_type}>
```



### Table
#### Send current row
```xhtml
    <h:form>
        <h:dataTable value="#{personData.persons}" var="person">
......
            <h:column>
                <f:facet name="header">Delete</f:facet>
                <h:commandButton value="Delete"
                                 action="#{personData.deletePerson(person)}">
                </h:commandButton>
            </h:column>
.....
        </h:dataTable>
    </h:form>

```
in backend there has to be model classs **PersonData** with method **deletePerson(Person person)** which accepts one
 input parameter of type Person.

## Styling (CSS)
It is necessary to create following structure in project *resources/static/style.css*
In XHTML then you can reference it through
```xhtml
    <link rel="stylesheet" type="text/css" href="style.css"/>
```

There should be also other possibility through
```xhtml
  <h:outputStylesheet library="default" name="css/style.css" />
```
but I wasn't able to handle it.

# Primefaces
It is library which is on top of JSF. To integrate to projet it is necessary add dependency:
```xml
<dependency>
    <groupId>org.primefaces</groupId>
    <artifactId>primefaces</artifactId>
</dependency>
```
and also add some template e. g.
```xml
<dependency>
    <groupId>org.primefaces.extensions</groupId>
    <artifactId>all-themes</artifactId>
    <version>1.0.8</version>
</dependency>
```

To *application.properties* add
```properties
jsf.PROJECT_STAGE=Development
primefaces.theme=overcast
```

To XHTML file it is necessary to add namespace
```XHTML
xmlns:p="http://primefaces.org/ui"
```

It is necessary to restart server. In XHTML then it is possible to use many Primefaces UI element.

## Theme switch
There is some default theme which is used. In **application.properties** it SHOULD be possible to change theme through
*jsf.primefaces.theme* key but it doesn't work.

Therefore in **ServletConfiguration** class there was added
```java
    @Value("${jsf.primefaces.theme:afterdark}")
    String primafacesTheme; 

```
This annotation automatically inject value *jsf.primefaces.theme* from application.properties file to variable
 **primafcesTheme*. In case that this value in properties file isn't specified, default value is used (after colon
 ). Then this value is used in

```java
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        sc.setInitParameter("primefaces.THEME", primafacesTheme);
    }  
```

# Joinfaces
It is project which autoconfigure PrimeFaces, Mojarra, JSF and many otheers. [More details](http://joinfaces.org/)
 

# Sources
<a name="coreServlets">[1] [CoreServlets](http://www.coreservlets.com/JSF-Tutorial/jsf2/#Beans-1)