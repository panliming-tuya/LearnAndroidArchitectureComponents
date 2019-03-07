# Android Architecture Components架构组件学习总览

Android architecture components 是一组库，帮助用户构建设计健壮、可测试、可维护的应用程序。

* [lifecycle-aware components ](https://developer.android.com/topic/libraries/architecture/lifecycle) 帮助你管理 Activity 和 fragment 的生命周期
* [LiveData ](https://developer.android.com/topic/libraries/architecture/livedata)在底层数据发生更改时通知视图的数据对象
* [ViewModel ](https://developer.android.com/topic/libraries/architecture/viewmodel)存储UI相关的数据
* [Room ](https://developer.android.com/topic/libraries/architecture/room)是一个 SQLite 对象映射库。可以轻松将 SQLite 数据转换成 Java 对象。并且支持 Rxjava、LiveData
* ... and so on

目前这套组件被归类到了[ Jetpack ](https://developer.android.com/jetpack)中，不过 Jetpack 本身不是我们这系列文章关注点重点，我们仅学习 Architecture Components 这一系列组件。

我在学习这一系列组件时，觉得官网的示例不够清晰。API本身不是问题，而是其讲解一个组件的使用时总是混杂着其他组件，学习使用时不够爽利。

因此，我将从ViewModel这个比较底层且轻量的组件出发，一步步将其他组件讲解清楚。并且最后将通过项目来巩固学习。

这个系列中将包含以下系列文章，包含使用示例、源码解析和 Demo乃至一个项目，本系列将不断更新：

[Android Architecture Components架构组件学习系列（一）ViewModel 使用及源码解析]()

Android Architecture Components架构组件学习系列（二）LifeData 使用及源码解析

Android Architecture Components架构组件学习系列（三）lifecycle 使用及源码解析

Android Architecture Components架构组件学习系列（四）data binding 使用及源码解析

Android Architecture Components架构组件学习系列（五）Room 使用及源码解析

Android Architecture Components架构组件学习系列（六）Demo级实战

Android Architecture Components架构组件学习系列（七）项目级实战

> 一篇文章写完后上面的目录会变成链接，希望你和我共同进步。

Demo 的源码版本将同时包括 [Java]() 版和 [Kotlin]() 版，也可以当做学习Kotlin的教程。

下面我们先总览一下官方的应用架构指南，防止陷入只见树木不见森林的状况。

## 应用架构指南

### 推荐应用架构

Google官方提供的架构图，该图显示了设计应用后所有模块应如何相互交互：

![](/images/final-architecture.png)

> 每个组件仅依赖于其下一级的组件。例如，Activity 和 Fragment 仅依赖于视图模型。存储区是唯一一个依赖于其他多个类的类；在本例中，存储区依赖于持久性数据模型和远程后端数据源。

Android官网这张图片的名字叫做final-architecture，意味着Google将这种架构视为Android应用架构的最佳实践。


### 两个原则
1. 关注点分离（[Separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns)，SoC）

	> “好的架构必须使每个关注点相互分离，也就是说系统中的一个部分发生了变化，不会影响其他部分。即使需要改变，也能够清晰地识别出那些部分需要改变。如果需要扩展架构，影响将会最小化，已经可以工作的每个部分都将继续工作。" 《AOSD中文版》
	
	关注点分离是实现上述目标的基本方法。关注点分离的基本方法有：
	
	* 按职责分离关注点
	
		将一个功能的实现分成展现层、业务层和数据层就是典型的按职责进行关注点分离的例子。
	
	* 按通用性分离关注点
		
		不同的通用程度意味着变化的可能性不同。可以将组成系统的元素分成技术通用部分、领域通用部分和特定应用部分。技术通用部分具有广泛的通用性，领域通用部分在对应领域具有普遍通用性。特定应用部分一般没有通用性。
	
	* 按粒度级别分离关注点
	
		在软件架构设计中，可以优先考虑大粒度的子系统和在集成系统中的互操作，忽略子系统的进一步分离。

2. 通过模型驱动界面

	最好是持久性模型。
	
	模型是负责处理应用数据的组件。它们独立于应用中的 View 对象和应用组件，因此不受应用的生命周期以及相关关注点的影响。
	
	持久性是理想之选，原因如下：

	* 如果 Android 操作系统销毁应用以释放资源，用户不会丢失数据。
	* 当网络连接不稳定或不可用时，应用会继续工作。

	应用所基于的模型类应明确定义数据管理职责，这样将使应用更可测试且更一致。

