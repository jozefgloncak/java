[&#8593;](../README.md)


# About
In this example there is demonstrate usage of __scopes__ in spring framework.
There are demonstrated both attitudes:

* XML - defined beans for __Message__ class
* annotations - defined beans for __Envelope__ class

In both cases are defined 2 types of scope - singleton, prototype.

In running application there is request to application context to get instance for bean __Message__ and __Envelope__
. If bean defined as singleton is returned than after requesting 2 instances and setting its property the same value is
 returned (because of the same instance).
. If bean defined as prototype is returned than after requesting 2 instances and setting its property various value si
 returned (because of various instances).