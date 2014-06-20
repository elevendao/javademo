/**
 * 版权所有：美创科技
 * 项目名称:web-demo-spring
 * 创建者: liushuai
 * 创建日期: 2014-6-20
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-6-20
 */
package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author liushuai
 *
 */
@Aspect
public class LogAspect {

	@Pointcut("within(controller..*)")
	public void inControllerPackagePointcut() {
	}
	
	@Pointcut("execution(public * save*(..))")
	public void executeBusinessPointcut() {
		System.out.println("================");
		System.out.println("aspect log.");
	}
}
