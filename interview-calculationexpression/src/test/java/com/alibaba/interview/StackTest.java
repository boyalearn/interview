package com.alibaba.interview;

import org.junit.Test;

public class StackTest {
	
	@Test
	public void test() {
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(1);
		stack.pop();
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		for(int i=0;i<28;i++) {
			System.out.println("top:"+stack.getTop());
			System.out.println("ele:"+stack.pop());
			
		}
	}
}
