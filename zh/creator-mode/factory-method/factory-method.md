# 工厂方法模式

## 定义
工厂方法模式(Factory Method Pattern)又称为工厂模式，也叫虚拟构造器(Virtual Constructor)模式或者多态工厂(Polymorphic Factory)模式，它属于类创建型模式。在工厂方法模式中，工厂父类负责定义创建产品对象的公共接口，而工厂子类则负责生成具体的产品对象，这样做的目的是将产品类的实例化操作延迟到工厂子类中完成，即通过工厂子类来确定究竟应该实例化哪一个具体产品类。
## 类图

![](https://rawgit.com/jasonim/design-patterns/develop/zh/creator-mode/factory-method/image/factory-method.svg)

代码：
```java
public static void main(String[] args) {
        //Http
        ProtocolFactory factory = new HTTPFactory();
        Protocol protocol = factory.createProtocol();
        protocol.performRequest();

        //UDP
        factory = new UDPFactory();
        protocol = factory.createProtocol();
        protocol.performRequest();

        //TCP
        factory = new TCPFactory();
        protocol = factory.createProtocol();
        protocol.performRequest();

}
```

上面的代码， 分别通过不同的协议工厂创建不过协议类， 方便客户端调用。这样客服端不需要关系哪种具体产品类将被实例化这一细节，也不许知道具体的类的类名， 当再有更多协议更方便扩展，也遵循「对扩展开放，对修改关闭」的原则。如果细心的同学可能会发现， 三段代码有相同的地方， 是的， 可以进一步重构， 想这样`createFactory(int protocol)`，这里为了更方便大家看懂工厂方法，就分开来写了。

运行结果：
![](https://rawgit.com/jasonim/design-patterns/develop/zh/creator-mode/factory-method/image/factory-method-run.png)

**优点**：
+ 在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还向客户隐藏了哪种具体产品类将被实例化这一细节，用户只需要关心所需产品对应的工厂，无须关心创建细节，甚至无须知道具体产品类的类名。
+ 基于工厂角色和产品角色的多态性设计是工厂方法模式的关键。它能够使工厂可以自主确定创建何种产品对象，而如何创建这个对象的细节则完全封装在具体工厂内部。工厂方法模式之所以又被称为多态工厂模式，是因为所有的具体工厂类都具有同一抽象父类。
+ 使用工厂方法模式的另一个优点是在系统中加入新产品时，无须修改抽象工厂和抽象产品提供的接口，无须修改客户端，也无须修改其他的具体工厂和具体产品，而只要添加一个具体工厂和具体产品就可以了。这样，系统的可扩展性也就变得非常好，完全符合“开闭原则”。

**缺点**
+ 在添加新产品时，需要编写新的具体产品类，而且还要提供与之对应的具体工厂类，系统中类的个数将成对增加，在一定程度上增加了系统的复杂度，有更多的类需要编译和运行，会给系统带来一些额外的开销。
+ 由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义，增加了系统的抽象性和理解难度，且在实现时可能需要用到DOM、反射等技术，增加了系统的实现难度。

## 使用场景
当我们遇见下列场景，可以考虑工作方法模式：
+ 我们不能预先知道被创建对象的类型，如我们在不知道发送网络请求用什么协议（TCP、UPD 或 HTTP）， 就像上面的例子。
+ 一个类想要他的子类指定它的创建的实例
+ 将创建的对象的任务委托给多个工厂子类中的其中一个， 调用者在使用时可以无需关系是哪个工厂子类创建产品， 需要时动态指定， 可将具体工厂类名存储在配置文件或数据中。

## Android 应用实例
Android 中用到了太多的工厂类，其中有用工厂方法模式的，比如AsyncTask类中工厂的具体实现如下：
```java
//工厂实现类
private static final ThreadFactory sThreadFactory = new ThreadFactory() {
    private final AtomicInteger mCount = new AtomicInteger(1);

    public Thread newThread(Runnable r) {
        return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
    }
};
```
上面代码中 ThreadFactory 就是工厂方式的类的实现。
