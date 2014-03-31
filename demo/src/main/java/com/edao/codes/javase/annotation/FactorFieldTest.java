/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-31
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-31
 */
package com.edao.codes.javase.annotation;

import java.lang.reflect.Field;

/**
 * @author liushuai
 *
 */
public class FactorFieldTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Field[] fields = AuditBean.class.getDeclaredFields();
		for (Field f : fields) {
			boolean isAnnotationPresent = f.isAnnotationPresent(FactorField.class);
			if (isAnnotationPresent) {
				FactorField ff = f.getAnnotation(FactorField.class);
				
				String value = ff.value();
				boolean isRuleElement = ff.isRuleElement();
				System.out.println("value=" + value + ", isRuleElement=" + isRuleElement);
			}
		}
	}

}
