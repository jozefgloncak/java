[&#8593;](../README.md)

# About
Usage of __Optional__ class. Usage is demonstrated on JUnit tests with Mockito.
* [orElse() and orElseGet() methods of Optional class](src/test/java/gloncak/jozef/java8/optional/OptionalElseTest.java)
* [raising concrete exception if value of Optional instance is null](src/test/java/gloncak/jozef/java8/optional/OptionalExceptionRaisingTest.java)
* [mapping original Optional instance to one which contains other type value](src/test/java/gloncak/jozef/java8/optional/OptionalIfChainingTest.java) - is used if it is required to read some
 value placed deeply in several objects (in object, in object....) to avoid checking whether accessing attribute is
  not null (to use it for calling of getter method).