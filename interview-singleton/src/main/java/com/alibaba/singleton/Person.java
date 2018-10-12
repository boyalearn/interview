package com.alibaba.singleton;
/**
 * ����ģʽ������ʽʵ��
 * @author zouhuixing
 *
 */
public class Person {
	private static Person instance;
	
	private Person() {
		try {
			//ģ��һ����ʱ�Ĺ������
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
