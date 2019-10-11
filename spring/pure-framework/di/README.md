[&#8593;](../README.md)

# About

Demonstrate dependency injection:

* XML - in _Beans.xml_ there are specified bean for __Name__ and __Address__. This beans are gradually used for
 creating bean __Person__ via:
  * constructor parameters - see _ref_ word <constructor-arg> element in xml file
  * properties - see _ref_ word <property> element in xml file
  * property namespace - see usage of attribute p:{property_name} or p:{property_name}-ref attributes of <bean> element.

* JAVA annotations - it is necessary to use annotation 
[__@ComponentScan__](src/main/java/gloncak/jozef/springframework/pure/di/config/AppConfig.java). Then it is (as in
 XML) possible to inject by annotating with @Autowired:
  * [constructor](src/main/java/gloncak/jozef/springframework/pure/di/beans/PersonAutowiredOnConstructor.java)
  * [setters](src/main/java/gloncak/jozef/springframework/pure/di/beans/PersonAutowiredOnSetter.java)
  * [class properties](src/main/java/gloncak/jozef/springframework/pure/di/beans/PersonAutowiredOnProperties.java)
  
  See in JAVA annotation configuration usage of __@Qualifier__ annotations which says Spring which concrete instance
   (named instance in __AppConfig__) should be used during injection.