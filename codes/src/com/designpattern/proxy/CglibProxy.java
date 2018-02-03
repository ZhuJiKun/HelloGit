package com.designpattern.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


//2. 实现对象
class Cat {

	public void eat() {
		System.out.println("cat eat.");
	}

	public void sleep() {
		System.out.println("cat sleep.");
	}
}

public class CglibProxy {

	
	/**
	 *  静态代理与动态代理都需要 被代理的对象 实现 一个接口， 根据接口去实现代理类的方法。
	 *  
	 *  Cglib代理是 采用继承的方式， 继承 被代理类， 被代理的类不需要实现接口
	 *  
	 *    Cglib代理主要通过对 字节码 的操作，为对象引入间接级别，以控制对象的访问。
	 *    Cglib底层使用了ASM（一个短小精悍的字节码操作框架）来操作字节码生成新的类。
	 *  
	 */
	
	public static void main(String[] args) {
		
		Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Cat.class); // 继承的方式
       
        enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
				
				System.out.println("before method");
		        Object o = proxy.invokeSuper(obj, args);
		        System.out.println("after method");
		        return o;
			}
        });
        Cat proxy = (Cat) enhancer.create();
        proxy.eat();
        proxy.sleep();
        
        System.out.println("------------");
        
        // 可以过滤方法
        Enhancer enhancer1 = new Enhancer();
        enhancer1.setSuperclass(Cat.class); // 继承的方式
       
        enhancer1.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
				if (method.getName().equals("eat")) {
					System.out.println("before method");
			        Object o = proxy.invokeSuper(obj, args);
			        System.out.println("after method");
			        return o;
				}
				return proxy.invokeSuper(obj, args);
			}
        });
        Cat proxy1 = (Cat) enhancer1.create();
        proxy1.eat();
        proxy1.sleep(); // 这个没有被处理
        
        // 由于采用的是继承的方式， Enhancer不能够拦截final方法
        
        
        
	}
	
	
	
}
