package com.alibaba.interview;

/**
 * ���ʽ��ֵ��������
 * @author zouhuixing
 *
 */
public class CalculationExpression {
	
	/**
	 * �������ȼ��㷨����
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
	
	//������ջ
	private Stack<Integer> optd=new Stack<Integer>();
	
	
	//������ջ
	private Stack<Character> optr=new Stack<Character>();

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
    private Stack<Integer> allOpData=new Stack<Integer>();
	
	public Stack<Integer> getAllOpData() {
		return allOpData;
	}
	
	/**
	 * ������ʽֵ
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
	 * �ݹ���������������
	 * @param curr
	 * @param beforIsOpDataMark
	 * @return
	 * @throws ExpressionException
	 */
	private Boolean exec(char curr,Boolean beforIsOpDataMark) throws ExpressionException {
		
		if(OpData.isOpData(curr)) {
			
			//����ǲ�������
			if(beforIsOpDataMark) {
				Integer data=optd.pop();
				optd.push( data*10+(curr-'0'));
				
				//�ռ����в�����
				Integer opData=this.allOpData.pop();
				this.allOpData.push( (opData*10+(curr-'0')));
			}else {
				optd.push(curr-'0');
				//�ռ����в�����
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
					//������
					optr.push(curr);
					
				} break;
				case '=' : {
					//ȡ������
					optr.pop();
				} break;
				case '>' : {
					Integer data1=optd.pop();
					Integer data2=optd.pop();
					optd.push(calculateTwoData(data1,data2,befor));
					optr.pop();
					beforIsOpDataMark=exec(curr,beforIsOpDataMark);
					
				} break;
				default : throw new ExpressionException("���ʽ�д�");
			}
		}else {
			throw new ExpressionException(curr+"�Ǳ��ʽ����");
		}
		return beforIsOpDataMark;
	}
	
	/**
	 * ����������������������
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
		throw new ExpressionException(op+"������쳣");
	}
	
	/**
	 * �ƶ����������ѡ��
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
		throw new ExpressionException(a+"�Ǳ��ʽ����");
	}
	
	
}
