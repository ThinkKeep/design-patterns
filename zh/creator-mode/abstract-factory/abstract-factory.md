# 抽象工厂模式
在工厂方法模式中具体工厂负责生产具体的产品，每一个具体工厂对应一种具体产品，工厂方法也具有唯一性，一般情况下，一个具体工厂中只有一个工厂方法或者一组重载的工厂方法。我们可以想一下，有时候我们需要一个工厂可以提供多个产品对象，而不是单一的产品对象，即产品族。如在工厂方法中 HTTPFactory 只负责生产 HTTPProtocol 等。显然不能满足我们的需求了， 怎么办？ 抽象工厂模式给我们解决了这个问题。

为了让你能够刚好的理解抽象工厂模式， 我们先了解一下几个概率：
+ **产品等级结构** ：产品等级结构即产品的继承结构，如一个抽象类是 Protocol，其子类有 HTTPProtocol、TCPProtocol、UDPProtocol，则抽象 Protocol与具体协议之间构成了一个产品等级结构，抽象Protocol是父类，而具体协议的 Procotol 是其子类。
+ **产品族**：在抽象工厂模式中，产品族是指由同一个工厂生产的，位于不同产品等级结构中的一组产品，如中国麦当劳店生产的巨无霸、可乐， 巨无霸位于汉堡产品等级结构中，可乐位于饮料产品等级结构中。


## 定义
Provide an interface for creating families of related or dependent objects without specifying their concrete classes。为创建一组相关或相互依赖的对象提供一个接口，而且无需指定它们的具体类。

## 实例 
大家都去麦当劳吃过汉堡吧， 还有中国的麦当劳产品和国外的麦当劳，不一样吧， 至少原料不一样吧， 鸡肉都是本地产的吧，还有汉堡的大小不一样吧。套餐的品种也不样吧， 下面我们就用麦当劳举例吧。

**类图**：


**代码**：
**AbsFactory**
```java
public interface AbsFactory {
    Hamburg createhamburg();
    Drink createDrink();
    //套餐....
}
```
**Store**
```java
public class Store {
    public static void main(String[] args) {
        AbsFactory factory = new ChineseFactory();
        factory.createDrink();
        factory.createhamburg();

        factory = new NYFactory();
        factory.createDrink();
        factory.createhamburg();
    }
}
```

**运行结果**：


**优点**：
+ 封装性，每个产品的实现类不是高层模块要关系的，要关心的是什么？是接口，是抽象，它不关心对象是如何创建出来，这由谁负责呢？工厂类。
+ 增加新的具体工厂和产品族很方便，无须修改已有系统，符合“开闭原则”。
+ 抽象工厂模式隔离了具体类的生成，使得客户并不需要知道什么被创建。由于这种隔离，更换一个具体工厂就变得相对容易。所有的具体工厂都实现了抽象工厂中定义的那些公共接口，因此只需改变具体工厂的实例，就可以在某种程度上改变整个软件系统的行为。另外，应用抽象工厂模式可以实现高内聚低耦合的设计目的，因此抽象工厂模式得到了广泛的应用。

**缺点**：
+ 抽象工厂模式的最大缺点就是产品族扩展非常困难，如实例中如果再添加新产品， 就需要在抽象工厂类添加对应的产品，开闭原则的倾斜性。

## 使用场景
+ 当系统所提供的工厂所需生产的具体产品并不是一个简单的对象，而是多个位于不同产品等级结构中属于不同类型的具体产品时需要使用抽象工厂模式。
+ 系统中有多于一个的产品族，而每次只使用其中某一产品族。
+ 系统提供一个产品类的库，所有的产品以同样的接口出现，从而使客户端不依赖于具体实现。

如某个 APP多套主题时， 青春版、老人版、自恋办等， 可以考虑抽象工厂模式。


## 抽象工厂模式与工厂方法模式区别及联系
抽象工厂模式与工厂方法模式最大的区别在于，工厂方法模式针对的是一个产品等级结构，而抽象工厂模式则需要面对多个产品等级结构，一个工厂等级结构可以负责多个不同产品等级结构中的产品对象的创建 。当一个工厂等级结构可以创建出分属于不同产品等级结构的一个产品族中的所有对象时，抽象工厂模式比工厂方法模式更为简单、有效率。

他们自己没有绝对界限，当抽象工厂模式中每一个具体工厂类只创建一个产品对象，也就是只存在一个产品等级结构时，抽象工厂模式退化成工厂方法模式；当工厂方法模式中抽象工厂与具体工厂合并，提供一个统一的工厂来创建产品对象，并将创建对象的工厂方法设计为静态方法时，工厂方法模式退化成简单工厂模式。

## Android 源码中的应用
在java 的连接数据库的操作中，对不同的数据库的操作而形成的对象操作族，就是一种很好的抽象工厂模式的应用。而在 Android 系统中 WebViewFactoryProvider/WebViewChromiumFactoryProvider 也应用抽象工厂。

**WebViewFactoryProvider**
```java
public interface WebViewFactoryProvider {
    ...

    WebViewProvider createWebView(WebView webView, WebView.PrivateAccess privateAccess);

    ...

    CookieManager getCookieManager();

}
```

**WebViewChromiumFactoryProvider**
```java
public class WebViewChromiumFactoryProvider implements WebViewFactoryProvider {

    @Override
    public WebViewProvider createWebView(WebView webView,
    	WebView.PrivateAccess privateAccess) {
        WebViewChromium wvc = new WebViewChromium(this,
        	webView, privateAccess);

        ...

        return wvc;
    }

    @Override
    public CookieManager getCookieManager() {
        synchronized (mLock) {
            if (mCookieManager == null) {

                ...

                mCookieManager = new CookieManagerAdapter(new AwCookieManager());
            }
        }
        return mCookieManager;
    }

    ...
}
```
WebViewFactoryProvider是抽象工厂接口，定义了WebView及周边功能所需要对象的创建方法，这些方法大部分为工厂方法，它们返回的对象类型是抽象的，属于面向接口的编程风格。抽象工厂WebViewFactoryProvider创建的对象有：实现Webview核心功能的WebViewProvider，管理Cookie的CookieManager，地理位置相关的GeolocationPermissions和存储Web表单数据的WebviewDatabase等等。WebViewChromiumFactoryProvider是抽象工厂的具体实现者，它提供“Chromium”主题的产品族，也是目前最新版本Webview正在使用的产品族。如果将来有新的浏览器引擎内核出现(以前用得是 Apple Webkit 内核， 听说 google 搞了一套自己的，不知道现在加进去没)，那么只需按照WebViewFactoryProvider接口创建该主题下产品，然后替换现有的「Chromium」就可以完成换代，不用改动其他地方任何代码。



