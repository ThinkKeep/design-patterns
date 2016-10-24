# 依赖关系
可以简单的理解，就是一个类A使用到了另一个类B，而这种使用关系是具有偶然性的、、临时性的、非常弱的，但是B类的变化会影响到A；比如某人要过河，需要借用一条船，此时人与船之间的关系就是依赖；表现在代码层面，为类B作为参数被类A在某个method方法中使用；

依赖关系是用一套带箭头的虚线表示的；

![](https://rawgit.com/jasonim/design-patterns/master/image/dependency.svg)
