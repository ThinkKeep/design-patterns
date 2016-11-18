#静态代理模式


##定义


> 为其它对象提供一种代理以控制对这个对象的访问。
> （Provide asurrogate or placeholder for another object to control access to it. ）

代理模式属于结构型模式。

代理模式共分为 **2** 种：

- **静态代理**
- **动态代理**


## 场景

代理模式中，由一个代理类代替真正的对象，客户端通过访问代理来进行操作，在操作的前后可以进行一些额外操作。

某个类能满足要求时，你当然可以直接用它，但当它不能满足要求，要扩展功能，根据 **开闭原则** 你又不能修改实现类代码，这时就用到了 代理 。


##类图

![](https://rawgit.com/jasonim/design-patterns/master/image/static-proxy.png)

在图中，代理与被代理实现同一个接口，即拥有共同的行为，同时代理中持有一个被代理的引用。这样当不想直接访问某个对象时，可以通过代理来间接访问。

##代码

###以宝强和经纪人举个栗子

1.经纪人和明星在一些行为上有共同点，所以定义一个共有接口：

	/**
	 * 明星接口，定义了一些明星的行为
	 * Created by zhangshixin on 8/19/2016.
	 */
	public interface IStar {
	    /**
	     * 参加节目
	     */
	    void attendTheShow();
	
	    /**
	     * 照顾妻子
	     */
	    void loveWife();
	
	    //...
	}

 
 2.接着是明星类，实现了明星接口的行为：
 
	/**
	 * 明星实体类
	 * Created by zhangshixin on 8/19/2016.
	 */
	public class Star implements IStar {
	    private String mName;
	
	    public Star(String name) {
	        mName = name;
	    }
	
	    @Override
	    public void attendTheShow() {
	        System.out.print( this.mName + " 参加演出 \n");
	    }
	
	    @Override
	    public void loveWife() {
	        System.out.print(this.mName + " 照顾了妻子");
	    }
	}


 3.经纪人要代表明星，就需要和明星有同样的行为，同时持有明星的引用：
	
	/**
	 * 经纪人
	 * Created by zhangshixin on 8/19/2016.
	 */
	public class Agent implements IStar {
	    /**
	     * 经纪人可以代表一个明星做一些决定
	     */

	    IStar mIStar;
	    boolean mIsHappy;
	
	    public Agent() {
			//内部持有一个被代理类的实例
	        mIStar = new Star("王宝强");
	    }
	
	    /**
	     * 代理可以在一定情况下拦截、修改被代理对象的行为，这里设置一个 “心情”的状态值
	     * @param isHappy
	     */
	    public Agent(boolean isHappy) {
	        mIStar new Star("王宝强");
	        mIsHappy = isHappy;
	    }
	
	    @Override
	    public void attendTheShow() {
	        mIStar.attendTheShow();
	
	    }
	
	    @Override
	    public void loveWife() {
	        if (mIsHappy) {
	            mIStar.loveWife();
	        } else {
	            //当经纪人心情不好时，就会干坏事
	            System.out.print("经纪人 照顾妻子");
	        }
	    }
	}



 4.调用方如何通过经纪人访问明星呢：

	
	/**
	 * 外界环境，一般都是通过经纪人来接触明星
	 * Created by zhangshixin on 8/19/2016.
	 */
	public class Environment {
	
	    public static void main(String[] args) {
	        //有个经纪人叫宋吉吉
	        Agent songJJ = new Agent(false);
	        songJJ.attendTheShow();
	        songJJ.loveWife();
	    }
	}


 5.运行结果

![这里写图片描述](http://img.blog.csdn.net/20160820234505489)


 可以看到 **代理** 的主要作用是 **方法增强**，它可以在**不“惊动”被代理类**的情况下修改被代理类的行为。**这有助于系统解耦**。我们这里代理类和被代理类都是自己亲自敲好的，属于**静态代理**。

## 特点

###代理模式的优点

代理模式能够协调调用者和被调用者，在一定程度上降低了系 统的耦合度。
远程代理使得客户端可以访问在远程机器上的对象，远程机器 可能具有更好的计算性能与处理速度，可以快速响应并处理客户端请求。

###代理模式的缺点：
由于在客户端和真实主题之间增加了代理对象，因此 有些类型的代理模式可能会造成请求的处理速度变慢。
实现代理模式需要额外的工作，有些代理模式的实现 非常复杂。

###引申
上述例子在编码时就确定了代理、被代理对象关系，称为 **静态代理**，如果有大量的或者运行时才确定的代理行为，静态代理就不太合适了；

而在实际应用中 动态代理 更为常用，它可以使用反射，通过参数和代理方法自动生成代理的代码。

## Android 中的应用

Android AIDL 中，我们写好 aidl 文件，编译器会生成一个对应的 Java 文件，比如 写个 IMyService.aidl,生成对应的 IMyService.java 文件内容如下：

	public interface IMyService extends android.os.IInterface {  
	    public static abstract class Stub extends android.os.Binder implements aidl.pac.IMyService {  
	        public Stub() {  
	        }   
	        public static aidl.pac.IMyService asInterface(android.os.IBinder obj) {  
	        }  
	        public android.os.IBinder asBinder() {  
	        }  
	        public boolean onTransact(int code, android.os.Parcel data,    android.os.Parcel reply, int flags)  
	                throws android.os.RemoteException {  
	        }  

	        //远程服务代理
	        private static class Proxy implements aidl.pac.IMyService {  
	            private android.os.IBinder mRemote; 
	            Proxy(android.os.IBinder remote) {  
	            }  
	            public android.os.IBinder asBinder() {  
	            }  
	            public java.lang.String getInterfaceDescriptor() {  
	            }  
	            public java.lang.String getValue()  
	                    throws android.os.RemoteException {  
	        }  。  
	        static final int TRANSACTION_getValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);  
	    }  
	    public java.lang.String getValue() throws android.os.RemoteException;  
	} 

在上述代码中可以看到 IMyService 的静态内部类 Proxy 和 Sub 实现了同样的接口，同时代理中持有一个被代理的引用，这样 Proxy 就可以代替 Sub 发送、接收请求，同时做一些额外操作。
