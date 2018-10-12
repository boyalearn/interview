package com.alibaba.interview;

/**
 * 表达式错误异常
 * @author zouhuixing
 *
 */
public class ExpressionException extends Exception{

	private static final long serialVersionUID = 3705989989176837009L;
	
	public ExpressionException(String msg) {
		super(msg);
	}

}
