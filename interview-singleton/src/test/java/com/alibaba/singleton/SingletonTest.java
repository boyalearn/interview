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
 * ���̲߳��Ե���ģʽ
 * @author zouhuixing
 *
 */
public class SingletonTest {
	
	//��Ŷ���hashCode��ֵ����Ч�����ж����Ƿ���ͬһ������
	private static List<Person> hashCodeList=new ArrayList<Person>();
	
	
	public static synchronized void addPersion(Person p) {
		hashCodeList.add(p);
	}
	
	//�����̳߳�
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
	 * �����߳���
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
		
		//�ȴ������߳�ִ�����
		cdl.await();
		
		//check
		Assert.assertTrue(check());
		
	}
	

}
