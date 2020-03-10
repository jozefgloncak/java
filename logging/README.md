# Logging
slf4j is like API for logging libraries.
log4j is concrete implementation.

In this example *log4j.properties* file is defined outside of generated jar file to make it possible to configure logging 
without neccesity to change file inside JAR file. To make it possible class path entry for "." was added. see more details in pom
in part maven-assembly-plugin. Also copying of file outside of jar is done through resource where is in include defined this
file.

