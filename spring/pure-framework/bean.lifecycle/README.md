[&#8593;](../README.md)

# About
Example demonstrate how lifecycle hooks (for initialization and destroying) for Spring beans can be implemented by
 annotations or by XML file.
 
 Notice that in [__App.java__](src/main/java/gloncak/jozef/springframework/pure/bean/lifecycle/App.java) file there
  is call of method __registerShutdownHook()__ which is necessary to force application call destroy callbacks at the
   end of running of Spring application. 
