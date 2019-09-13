[&#8593;](../README.md)

# About
This example demonstrate design pattern adapter. Imagine that we have library (to keep is simple let's imagine that
 it is just one class) which contains complex e. g. mathematical functions. This library is widely used in legacy
 system. After same time we find out that there exist better implementation, which is even widely used and
 comprehensively tested. Rewrite of legacy system would be too complex, therefore adapter design pattern can be
 used where we just create new implementation of legacy library via aggregating new library.
 
# Source code
Legacy math library is represented as class __LegacyMathLibraryImpl__ which implements interface
 __LegacyMathLibrary__. Better (new) math library is repreented as class __BetterMathLibraryImpl__ which implements
  __BetterMathLibrary__ interface. Adapter which make it possible to use better math library in legacy system (via
   aggregation of instance of better math library and via calling it method while implementing legacy interface) is
    __BetterMathLibraryAdapter__. Whole usage is demonstrated in __Main__.

<img src="https://www.plantuml.com/plantuml/img/XPB1IWCn48RlUOevMhM2daMgzLBOYg0tyJ3TdQu3cIGa4s4HtzsqRc5hTp67C4p-cVb-9eka63MBDKPPBS7IOahGCBOH1RO-GaE9MmS1LIawk6ymbE3Ac7elhQZ5kdlfaf8OTkLfYpNrwJNgHyMRYB63BmFbN5ISz7PLT2t5kzAyzX9yebV_75deASk6OZehZS3juzblsIuXx5YoBBs8Tu_yoSd7W_Bw4lhePcQ-Z-4652i95hXS9EHqH6067XvePtd4Gv6ylKCAbhNddYPUPyreZsWFh4FIarb_IKy4KuNzWlvnELdcv_EHMSCjmtcHZBHSa6lAJ_e1">

# Program
Demonstrate creating instance of legacy math library which is then used in legacy system and later easy replacement
 with adapter which wrap new math library

# Test
In this example there are also parametrized JUnit test. Test is in class __MathLibraryImplTest__ and is called twice
. First time with _legacy math library_ and second time with _better math library_.