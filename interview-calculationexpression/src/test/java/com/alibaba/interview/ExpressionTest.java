package com.alibaba.interview;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionTest {
	@Test
	public void test() throws ExpressionException {
		CalculationExpression calculation=new CalculationExpression();
		calculation.setExpression("1+2");
		Assert.assertTrue(calculation.calculate()==3);
		Assert.assertTrue("[1,2]".equals(calculation.getAllOpData().toString()));
		
		calculation.setExpression("(1+20)*3+1*1000/100");
		Assert.assertTrue(calculation.calculate()==73);
		Assert.assertTrue("[1,20,3,1,1000,100]".equals(calculation.getAllOpData().toString()));
		
		
		calculation.setExpression("(1+20)*3+1*1000/100+2000");
		Assert.assertTrue(calculation.calculate()==2073);
		Assert.assertTrue("[1,20,3,1,1000,100,2000]".equals(calculation.getAllOpData().toString()));
	}
}
