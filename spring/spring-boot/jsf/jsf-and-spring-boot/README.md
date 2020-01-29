# About
Following remarks come mainly from tutorial [1](#coreServlets)

# Lifecycle of JSF
* getter method of bean is called once form is displayed
* setter methods are called once formular is submitted 
    * after that, method specified in action attribute of button is called. There can be several action methods if
     there is
 several buttons on form.


# Redirecting
in action define
```
action="{name_of_new_page?faces-redirect=true}"
```
[more](https://stackoverflow.com/questions/15521451/how-to-navigate-in-jsf-how-to-make-url-reflect-current-page-and-not-previous-o)


# faces-config.xml file
This file has to be situted as follows in main/webpp/WEB-INF/faces-config.xml

File starts like this
```xml
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

## Explicit navigation
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


# UI elements
## Selection elements
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



## Table
### Send current row
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

# Styling (CSS)
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

# Sources
<a name="coreServlets">[1] [CoreServlets](http://www.coreservlets.com/JSF-Tutorial/jsf2/#Beans-1)