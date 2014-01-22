package com.edao.codes.javase.text;



import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


/**
 * @author liushuai
 *
 */
public class NumberFormatTest {
	
	public void numberFormatTest() {
		NumberFormat nf = NumberFormat.getInstance();
		double dNum = 1.5868028935E10;
		String myNum = nf.format(dNum);
		System.out.println(myNum);
	}
	
	public void decimalFormatTest() {
		DecimalFormat df = new DecimalFormat();
		/*df.applyPattern("#");
		double dNum = 1.5868028935E10;
		String myNum = df.format(dNum);
		System.out.println(myNum);*/
		
		String numStr = "111111123,456,789";
		df.applyPattern("###,###,###");
		try {
			double d = df.parse(numStr).doubleValue();
			int i = df.parse(numStr).intValue();
			long l = df.parse(numStr).longValue();
			System.out.println(d);
			System.out.println(i);
			System.out.println(l);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		NumberFormatTest test = new NumberFormatTest();
		test.decimalFormatTest();
		
		double d = 3.12;
		System.out.println("d" + d);
	}
}
