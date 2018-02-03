package com.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理的本质是通过反射形成.java文件，再利用动态编译生成.class文件
 */

// 1. 需要代理实现的接口
interface Color {
	void paint();
}

//2. 实现对象
class Red implements Color {

	@Override
	public void paint() {
		System.out.println("red paint.");
	}
}

class Blue {

	public void paint() {
		System.out.println("blue paint.");
	}
}


// 代理
public class DynamicProxy {

	public static void main(String[] args) {
		
		// 第一种方式: 创建red对象的代理对象
		
		Color red = new Red();
		
		Object proxy = Proxy.newProxyInstance(red.getClass().getClassLoader(), 
				red.getClass().getInterfaces(), 
				new InvocationHandler() { 				// 增强类
				   /**
					* 在invoke方法编码指定返回的代理对象干的工作
					* proxy : 调用该方法的代理实例
					* method： 代理对象当前调用的方法
					* args  : 方法参数
					*/
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						System.out.println("before method");
						
						Object c = method.invoke(red, args);
						System.out.println("after method");
						return c;
					}
		});
		
		((Color)proxy).paint(); // 代理对象的方法
		
		
		/**
		 * JDK的动态代理是靠 多态和反射来实现的，生成的代理类需要实现传入的接口,并通过反射来得到接口的方法对象
		 * 并将此方法对象传参给 增强类 的invoke方法去执行,从而实现了代理功能，
		 * 接口是jdk动态代理的核心实现方式,没有接口就无法通过反射找到方法.
		 */
		
		// 没有实现接口的报错
		Blue blue = new Blue();
		
		Object proxy2 = Proxy.newProxyInstance(blue.getClass().getClassLoader(), 
				blue.getClass().getInterfaces(), 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						System.out.println("before method");
						
						Object c = method.invoke(blue, args);
						System.out.println("after method");
						return c;
					}
		});
		
		((Blue)proxy2).paint(); // 代理对象的方法
		
		
	}
	
}
