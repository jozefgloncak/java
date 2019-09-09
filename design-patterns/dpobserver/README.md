# About
This example demonstrate design pattern Observer (Listener). In example is used class __NumberGenerator__ which is 
responsible for generating random integer numbers from 0 to 999. To this generator (Observable) are registered observers.
There are 3 type of observers for:
* even numbers - 2, 4, 6....
* odd numbers - 1, 3, 5....
* numbers divisible by 3 - 3, 6, 9, 12.....
 

# Source code
Source code is placed to the package _gloncak.jozef.design.pattern_ which consists of 3 subpackages:
* api - contains API (interfaces) for for __Observer__, __Observable__ and __NumberGenerator__
* impl - concrete implementations of interfaces __Observer__   and __NumbeGenerator__
* writer - helping class for easier file log outputing cross whole application.

# Program
After starting of program generation of numbers will automatically start. Logs from generating are stored in _output.txt_.
In console you can specify several commands (see it by typing _help_ in running program):
- start - to start generation of numbers:
- stop - to stop generation of numbers
- quit - to quit application
- register - register new observer
     - even
     - odd
     - 3
- list - list registered observers
- unregister - unregister existing observer
  - [id] - just type id which can be find out from _list_ command
  
If command consists of two words (e. g. register odd or unregister 1), then every word is defined on standalone line.
Running generator can be stopped by command _stop_ and again started via command _start_. If generator is running you
can use command _list_ to find out which are currently registered observers. Command _unregister_ can be used to unregister
registered observer (ID specification is required). Command _register_ can be used to register additional observers.
At start of program 1 instance of every type of observer is created. 
