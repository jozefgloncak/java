[&#8593;](../README.md)

# About
This example demonstrate design pattern servant. In example is used dummy service for calculation of property
 insurance (servant). There is several type of property. Data necessary for insurance calculation for every property
  type are specified in various manner. Every type of property implements common interface which can be consumed by
   servant.

# Source code
Servant class is defined in class __InsuranceCounter__. This class contains method _countInsurance(Property property
)_. Input parameter is of type __Property__. It is interface which must implement every property which wants to use
 this servant (service). As example there are 3 various implementation of property:
 * __BlockOfFlat__
 * __Cottage__
 * __House__
 
 Every of this instances implements interface __Property__ and so can be used as input of _countInterface_ method.

# Program
In main method there is demonstrated insurance calculation for every of 3 above mentioned property type. In log
 output (default to console) you will see 3 lines with specified property type and insurance value.