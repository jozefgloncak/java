[&#8593;](../README.md)

# About
Demonstration of functional interfaces. Examples are implemented as JUnit tests and are separated to 5 classes:
* ConsumerTest - receive input parameter(s) and return nothing. (Implementation of method __accept()__)
* FunctionTest - receive input parameter(s) and return new value - similar to mathematical function. (Implementation
 of method __apply()__)
* OperatorTest - receive input parameter(s) and return value. All values (paramter, return value) are of the same
 type (like mathematical operands). (Implementation of method __apply()__) 
* PredicateTest - receive input parameter(s) and return boolean value. (Implementation of method __test()__)
* SupplierTest - receive NO parameter and return value. (Implementation of method __get()__1)