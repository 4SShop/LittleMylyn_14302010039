# LittleMylyn_14302010039
A plug-in of eclipse used to manage tasks during development 

相关规则：  
1.task的名称不会重复，可以作为识别task的id  
2.可以将task设置为debug、new feature、refactor这三种类型  
3.task的状态有New、Activated、Finished这三种，同一时刻只能有一个处于Activated状态的task  
4.当一个task处于Activated状态时，打开或修改一些class的时候将会将其与该task相关联  
5.当一个已经建立过关联类的task再次处于Activated的时候，task下会出现相关联的类名，并且可以打开  

