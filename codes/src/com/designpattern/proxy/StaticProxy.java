package com.designpattern.proxy;

/**
 * 静态代理：你不知道我想做什么，我也不关心你做了什么
 *    单独写一个代理类，传入代理对象的引用，代码里就写好需要添加的逻辑
 */

// 1. 需要代理实现的接口
interface Animal {
	void eat();
	void sleep();
}

// 2. 实现对象
class Dog implements Animal {

	@Override
	public void eat() {
		System.out.println("dog eat.");
	}

	@Override
	public void sleep() {
		System.out.println("dog sleep.");
	}
}

/*
 *  3. 代理类, 传入需要代理的对象
 *       代理类必须与被代理类实现 同一个接口 ，因为在实际开发中不会关注被代理类的内部方法，只有实现同一个接口，才能获得被代理类的所有的方法(Override)，否则拿不到方法，无法写代理逻辑。
 */
public class StaticProxy implements Animal {
	
	private Animal animal;
	
	public StaticProxy(Animal animal) {
		this.animal = animal;
	}

	@Override
	public void eat() {
		System.out.println("before eat.");
		animal.eat();
		System.out.println("after eat.");
	}

	@Override
	public void sleep() {
		System.out.println("before sleep.");
		animal.sleep();
		System.out.println("after sleep.");
	}	
	
	
	public static void main(String[] args) {
		Animal dog = new Dog();
		StaticProxy proxy = new StaticProxy(dog);
		
		proxy.eat();   // 执行代理对象，执行的是处理后的方法
		proxy.sleep();
	}
}