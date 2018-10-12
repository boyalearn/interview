package com.alibaba.interview;

/**
 * ²Ù×÷Êı
 * @author zouhuixing
 *
 */
public class OpData {
	
	private final static char[] elem=new char[] {
			'0','1','2','3','4','5','6','7','8','9'
	};
	
	public static boolean isOpData(char e) {
		for(int i=elem.length-1;i>=0;i--) {
			if(e==elem[i]) {
				return true;
			}
		}
		return false;
	}
}
