package com.alibaba.singleton;
/**
 * 单利模式，懒汉式实现
 * @author zouhuixing
 *
 */
public class Person {
	private static Person instance;
	
	private Person() {
		try {
			//模拟一个耗时的构造过程
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized Person getInstance() {
		if(null==instance) {
			instance=new Person();
		}
		return instance;
	}

	
	
}
