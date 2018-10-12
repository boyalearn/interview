package com.alibaba.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
/**
 * 多线程测试单例模式
 * @author zouhuixing
 *
 */
public class SingletonTest {
	
	//存放对象hashCode的值用于效验所有对象是否是同一个对象。
	private static List<Person> hashCodeList=new ArrayList<Person>();
	
	
	public static synchronized void addPersion(Person p) {
		hashCodeList.add(p);
	}
	
	//测试线程池
	ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 20,
			                        TimeUnit.SECONDS,
                                    new LinkedBlockingDeque<Runnable>(100),
                                    new ThreadPoolExecutor.CallerRunsPolicy());

	
	
	private boolean check() {
		for(int i=1;i<100;i++) {
			if(!(hashCodeList.get(i-1)==hashCodeList.get(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 测试线程类
	 * @author zouhuixing
	 *
	 */
	public class TestThread implements Runnable{
		
		private CountDownLatch cdl;
		
		public TestThread(CountDownLatch cdl) {
			this.cdl=cdl;
		}

		@Override
		public void run() {
			Person person=Person.getInstance();
			SingletonTest.addPersion(person);
			System.out.println(person.hashCode());
			cdl.countDown();
		}
	}
	
	@Test
	public void test() throws InterruptedException, ExecutionException {
		
		CountDownLatch cdl = new CountDownLatch(100);
		
		for(int i=0;i<100;i++) {
			TestThread t=new TestThread(cdl);
			threadPool.execute(t);
		}
		
		//等待所有线程执行完成
		cdl.await();
		
		//check
		Assert.assertTrue(check());
		
	}
	

}
