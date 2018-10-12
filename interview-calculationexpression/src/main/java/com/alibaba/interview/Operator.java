package com.alibaba.interview;
/**
 * ²Ù×÷·û
 * @author zouhuixing
 *
 */
public class Operator {
	
	public final static char[] elem=new char[] {
			'+','-','*','/','(',')','#'
	};
	public static boolean isOperator(char e) {
		for(int i=elem.length-1;i>=0;i--) {
			if(e==elem[i]) {
				return true;
			}
		}
		return false;
	}
}
