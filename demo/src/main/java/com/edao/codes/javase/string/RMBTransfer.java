/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-21
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-21
 */
package com.edao.codes.javase.string;

/**
 * @author liushuai
 *
 */
public class RMBTransfer {
	
	static String[] chNum = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	static String[] pos = {"", "拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟"};
	static String unit = "元";
	
	// （￥1011）－>（一千零一拾一元整）
	public static String toChinese(int num) {
		String arabics = String.valueOf(num);
		//System.out.println(arabics);
		int len = arabics.length();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<arabics.length(); i++) {
			char ch = arabics.charAt(i);
			int iNum = ch-48;
			int iPos = len - i - 1;
			sb.append(chNum[iNum]);
			if (iNum != 0) {
				sb.append(pos[iPos]);
			}
		}
		sb.append(unit);
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String money = toChinese(2123323041);
		System.out.println(money);
	}

}
