package com.alibaba.interview;

/**
 * 表达式求值核心运算
 * @author zouhuixing
 *
 */
public class CalculationExpression {
	
	/**
	 * 定义优先级算法数组
	 */
	private char[][] level=new char[][] {
		{'>','>','<','<','<','>','>'},
		{'>','>','<','<','<','>','>'},
		{'>','>','>','>','<','>','>'},
		{'>','>','>','>','<','>','>'},
		{'<','<','<','<','<','=',' '},
		{'>','>','>','>',' ','>','>'},
		{'<','<','<','<','<',' ','='},
	};
	
	
	private String expression;
	
	//操作数栈
	private Stack<Integer> optd=new Stack<Integer>();
	
	
	//超作符栈
	private Stack<Character> optr=new Stack<Character>();

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
    private Stack<Integer> allOpData=new Stack<Integer>();
	
	public Stack<Integer> getAllOpData() {
		return allOpData;
	}
	
	/**
	 * 计算表达式值
	 * @return
	 * @throws ExpressionException
	 */
	public int calculate() throws ExpressionException {
		
		this.allOpData=new Stack<Integer>();
		char[] exprArr=(expression+"#").toCharArray();
		
		Boolean beforIsOpDataMark=false;
		optr.push('#');
		
		
		for(int i=0;i<exprArr.length;i++) {
			char curr=exprArr[i];
			if(Character.isWhitespace(curr)) {
				continue;
			}
			beforIsOpDataMark=exec(curr,beforIsOpDataMark);
		}
		
		return Integer.valueOf(optd.getTop());
	}
	/**
	 * 递归解析并且运算操作
	 * @param curr
	 * @param beforIsOpDataMark
	 * @return
	 * @throws ExpressionException
	 */
	private Boolean exec(char curr,Boolean beforIsOpDataMark) throws ExpressionException {
		
		if(OpData.isOpData(curr)) {
			
			//如果是操作数，
			if(beforIsOpDataMark) {
				Integer data=optd.pop();
				optd.push( data*10+(curr-'0'));
				
				//收集所有操作数
				Integer opData=this.allOpData.pop();
				this.allOpData.push( (opData*10+(curr-'0')));
			}else {
				optd.push(curr-'0');
				//收集所有操作数
				this.allOpData.push( (curr-'0') );
			}
			beforIsOpDataMark=true;
		}else if( Operator.isOperator(curr) ){
			beforIsOpDataMark=false;
			if(optr.isEmpty()) {
				return beforIsOpDataMark;
			}
			char befor=optr.getTop();
			switch(deduce(befor,curr)){
				case '<' : {
					//操作符
					optr.push(curr);
					
				} break;
				case '=' : {
					//取消符号
					optr.pop();
				} break;
				case '>' : {
					Integer data1=optd.pop();
					Integer data2=optd.pop();
					optd.push(calculateTwoData(data1,data2,befor));
					optr.pop();
					beforIsOpDataMark=exec(curr,beforIsOpDataMark);
					
				} break;
				default : throw new ExpressionException("表达式有错");
			}
		}else {
			throw new ExpressionException(curr+"非表达式符号");
		}
		return beforIsOpDataMark;
	}
	
	/**
	 * 对两个数进行算术操作。
	 * @param data1
	 * @param data2
	 * @param op
	 * @return
	 * @throws ExpressionException
	 */
	private  Integer calculateTwoData(Integer data1,Integer data2, char op) throws ExpressionException {
		switch(op) {
		case '+' : return data2+data1;
		case '-' : return data2-data1;
		case '*' : return data2*data1;
		case '/' : return data2/data1;
		}
		throw new ExpressionException(op+"运算符异常");
	}
	
	/**
	 * 推断运算符的优选级
	 * @return
	 * @throws ExpressionException 
	 */
	private char deduce(char a,char b) throws ExpressionException {
		return level[operatorToInt(a)][operatorToInt(b)];
	}
	
	private int operatorToInt(char a) throws ExpressionException {
		for(int i=0;i<Operator.elem.length;i++) {
			if(Operator.elem[i]==a) {
				return i;
			}
		}
		throw new ExpressionException(a+"非表达式符号");
	}
	
	
}
