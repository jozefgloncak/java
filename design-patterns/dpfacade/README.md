# About
This example demonstrate design pattern facade. Complex backend system represents system for evidence of citizenes of
whole world. This system consists of 2 subsystem. First one is for evidence of administrative units and 
 second one is for evidence of citizenes. Settlement belongs to one district. District consists
 of many settlements. Districts belongs to one state. State consists of many districts. There is also connection
  between settlement and Citizen - every citizen has permanent stay in a settlement.
 
 It is evident that for complex data about one person (name, name of settlement with permanent stay, name of district
 , name of state to which citizen belong to) it would be necessary to interact with many classes.
 
 #Source code
 Administrative part of backend system is modeled through __AdministrativeUnits__ which aggregate settlement
  (__Settlement__), districts (__District__), states (__States__). Citizen part is modeled through __Citizens__ which
   aggregate instances of __Citizen__.
 
 Facade to backend system described above is modeled through class __CitizenInfoFacade__ and complex data are
  transfered through DTO __CitizenInfoDTO__.
  
 <img src="https://www.plantuml.com/plantuml/img/XLJRRi8m37tFL-Hnq-0BDY4G20bfqnvW7-0rfehKfbLia1ZQlozjchRfXV5K7Vlu-CGsQsAmVCap8LXpXdAR0P5K6b8BkJmNLYeadHfP0ZDQ8_UGW48XaZhnoC0exqAwt_B8LfjK6iZH0zgmf2hZe3pmhedV7Bfo2kIo4oRn6shj6kXXkS0xx3hi6xF2f7Ggwcw6IeJiZELel5Pel4KVTz-c1GkHO6XvYd2wbTYPsshMFsWUYYK-fs-jH9k3STMSjTlGMMdngeiBrR6X4WMf3oYhz4K8h88EZJW7SoxyV0Dvet9jAfF0-efVHZD966C-kQc2r2dKWPTU-TtfKwR8-qkMzT2doFxpwsHd5NNk-HeWUgv555BHg1gmNivERCfj_nNLmY_yoXCf3eLbw2ztp6rtOxILpGK0moP9aCZTtLrK0NzWnNBPbXVjdLHeLrE8Tl8kq7oBAKirhtO-5ssrPkcTRDZiGJEHbueosZ4ncEr_ESrczchHJ7YYQtVuOer6kR-wFm00">
 
 #Program
 In main function there are 2 parts:
 - inicialization of backend system:
      - dummy data for subsystem with administration units - created state Slovakia with 2 districts (Banska Bystrica
      , Ruzomberok). In district Banska Bystrica there are 4 settlements and in Ruzomberok there is just 1.
      - dummy data for subsystem with citizenes - created 4 citizen where first 3 belongs to settlements from Banska
       Bystrica district and last one to settlement from Ruzomberok district.
  - accessing aggregated data through facade - there is request for detailed citizen information for 5 person.
  
  After starting program you will obtain in console log output. For first 4 person you will obtain detailed
   information. For 5th you just obtain true information that citizen with such ID hasn't been found.