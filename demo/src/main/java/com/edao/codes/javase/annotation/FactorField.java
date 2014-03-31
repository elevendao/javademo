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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author liushuai
 *
 */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Documented
public @interface FactorField {
	
	/**
	 *  默认值
	 */
	String DEFAULT ="#default";
	
	/**
	 * 设置的标注值
	 */
	String value() default DEFAULT;
	
	/**
	 * 设置是否是规则因子
	 */
	boolean isRuleElement() default false;
}
