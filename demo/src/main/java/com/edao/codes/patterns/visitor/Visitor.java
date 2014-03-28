/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-27
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-27
 */
package com.edao.codes.patterns.visitor;

/**
 * 访问者模式适用场景：
 * 如果我们为一个现有的类增加新的功能，不得不考虑以下问题：
 * 1.新功能会不会与现有功能出现兼容性问题？
 * 2.以后会不会需要再添加？
 * 3.如果类不允许修改代码怎么办？
 * 
 * 面对这些问题，最后的加法就是使用访问者模式，访问者模式
 * 适用于数据结构相对稳定的系统，把数据结构和算法解耦
 * 
 * @author liushuai
 *
 */
public interface Visitor {

	void visit(Subject subject);
}
