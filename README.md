# LittleMylyn_14302010039
A plug-in of eclipse used to manage tasks during development 

相关规则：  
1.task的名称不会重复，可以作为识别task的id  
2.可以将task设置为debug、new feature、refactor这三种类型  
3.task的状态有New、Activated、Finished这三种，同一时刻只能有一个处于Activated状态的task  
4.当一个task处于Activated状态时，打开或修改一些class的时候将会将其与该task相关联  
5.当一个已经建立过关联类的task再次处于Activated的时候，task下会出现相关联的类名，并且可以打开  

分工：  
魏子耘：
林航宇：将有关文件与相关的task关联  
梁旭：实体类以及dao层的实现  
朱潇：biz层的实现以及文档的撰写  

对eclipse可扩展性的理解：  
eclipse是一个开放源代码的、基于 Java 的可扩展开发平台。就其本身而言，它只是一个框架和一组服务，用于通过插件组件构建开发环境。换句话说，eclipse本身就是一系列的插件  
因此，eclipse具有极强的可扩展性，通过大量的可扩展点的提供，允许用户构建与eclipse环境无缝集成的插件  
每当eclipse 插件实现了一个扩展点，就创建了一个扩展，此外，扩展还可以创建自己的扩展点。这种插件模式的扩展和扩展点是递归的，因此被认为是非常灵活的。  

体会：  
在本次lab的实现中，我们不仅体会到了eclipse平台极强的可扩展性，更对团队合作开发项目有了进一步的了解。  
在本次的团队开发过程中，我们吸取了之前的教训与经验，试图合理分配每个成员的工作，并且提前定义了相应的接口解耦成员之间的工作。  
虽然在之后的工作中发现一开始接口的定义并未考虑周全，但是我们迅速对其进行完善，提高了开发的体验
