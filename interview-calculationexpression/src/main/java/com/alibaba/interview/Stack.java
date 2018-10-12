package com.alibaba.interview;
/**
 * 栈数据结构
 * @author zouhuixing
 *
 */
@SuppressWarnings("unchecked")
public class Stack<T> {
	private T[] stackElem;
	
	
	private int capacity=16;
	
	private int top;
	
	
	public Stack() {
		top=-1;
		stackElem=(T[])new Object[capacity];
	}
	public void push(T elem) {
		if(null==elem) {
			throw new NullPointerException("入栈数据不能为空");
		}
		top++;
		checkCapacity();
		stackElem[top]=elem;
	}
	/**
	 * 保证栈容量可用
	 */
	private void checkCapacity() {
		if(top>=capacity) {
			capacity*=2;
			T[] newStackElem=(T[])new Object[capacity]; 
			for(int i=0;i<top;i++) {
				newStackElem[i]=stackElem[i];
			}
			stackElem=newStackElem;
		}
	}
	
	public T pop() {
		if(top<0) {
			throw new NullPointerException("栈为空");
		}else {
			T d=stackElem[top];
			top--;
			return d;
		}
	}
	public T getTop() {
		if(top<0) {
			throw new NullPointerException("栈为空");
		}
		T d=stackElem[top];
		return d;
	}
	public boolean isEmpty() {
		if(top<0) {
			return true;
		}else {
			return false;
		}
	}
	public String toString() {
		StringBuffer str=new StringBuffer();
		str.append("[");
		for(int i=0;i<=top;i++) {
			str.append(""+stackElem[i]+",");
		}
		if(top>=0) {
			str.setLength(str.length()-1);
		}
		str.append("]");
		return str.toString();
	}
}
