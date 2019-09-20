[&#8593;](../README.md)

# About
This example demonstrate design pattern __comand__. There is collection of persons. Each of person should be at the
 end of it life destroyed.
 
This design pattern is in JAVA widely spreaded as functional interface _Consumer_. 

# Source code
Demonstration of pattern is in __Main__. There are implemented concrete commands. There is also interface
 __Archivator__ which has to be (or similar) implemented in design pattern command. Person is modeled via __Person__
  class.

<img src="https://www.plantuml.com/plantuml/img/RL4xRiCm3Drv2ev94ryWDOmKMLEWKwSkXCtO0YpIe9YYHj6xLvNZ84ErK7WVaayw9YVpQnm1FFZ8U1ef9Um2zKOHBshOSGgzu4JkR88dZP6a0sYByeqjgU0tO3wxh7NJUVDYxH0-oRETxjVjC_m01CbjBjGoFeZgcaHbZdfDJRCsMvoysMQRg3DQw0S_8BwJXQp44AUH8ukJ1vNzRHIZw-53i4B-AXjVmiZx0aoMfsSQMvMaAngrU51zuBzzZMPOuxrIk8SxX-JrahV1gTJq3tFReZ0BtREmaVEYI2KGbCwxgdbuXWLxgZAuI06EB5t-dLy0">

# Program
 Each person
 contains basic
 information and there is also method _destroy_ which is reponsible for cleaning person object (unregisterate
  observers, closing files....). Additionally this method also contains archivation parts which can be
   implemented differently according to selected archivation strategy. This is just place where __comand__ design
    pattern was used. There are following archivation strategies:
* write to console
* write to logger
* move to other array
* write to output file
Every strategy is standalone command in main class.