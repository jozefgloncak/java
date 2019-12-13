[&#8593;](../README.md)

# About
Demonstrate usage of annotation _@GeneratedValue_ with _SEQUENCE_, _IDENTITY_, _TABLE_ strategy

# SQL
In example there is one DB table BOOK.

# Source code
## SEQUENCE
In _Book_ entity there is set for property id:
* strategy - SEQUENCE
* initial value - 50

## IDENTITY
In __BookWithIdentity__ entity is just defined strategy

## TABLE
In __BookWithTable__ entity is defined strategy and also name of DB table where will be ID value stored. Table
 consist of 2 columns.
 
First is defined through @TableGenerator annotation parameter _pkColumnName_ and represents
  name of value for generation.

Second is defined through parameter _valueColumnName_ and represents current allocated value of table ID generator.