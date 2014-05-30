/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-b-2.6.0.0
 * 创建者: liushuai
 * 创建日期: 2014-1-22
 * 文件说明: 用于标注审计对象用于审计规则的字段
 * 最近修改者：liushuai
 * 最近修改日期：2014-1-22
 */
package com.edao.codes.solr.beans;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author liushuai
 *
 */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface FactorField {
	/**
	 *  默认值
	 */
	String DEFAULT ="#default";
	
	/**
	 * 返回设置的标注值
	 */
	String value() default DEFAULT;
}
