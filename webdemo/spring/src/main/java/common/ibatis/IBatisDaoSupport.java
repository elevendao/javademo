/**
 * 版权所有：美创科技
 * 项目名称:spring_ibatis
 * 创建者: liushuai
 * 创建日期: 2013-6-25
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-6-25
 */
package common.ibatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liushuai
 * 
 */
public class IBatisDaoSupport extends
		org.mybatis.spring.support.SqlSessionDaoSupport {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
