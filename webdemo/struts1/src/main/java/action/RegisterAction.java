package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import action.actionform.UserForm;

public class RegisterAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        /** 获取ActonForm封装的请求对象 */  
        UserForm userForm = (UserForm) form;   
        /** 打印输出信息 ---观察效果 */  
        System.out.println("用户名称：" + userForm.getUsername() + "用户密码："   
                        + userForm.getUserpass() + "   用户邮箱："   
                        + userForm.getUseremail());   
  
        try {   
            System.out.println("向数据库发送数据处理.....");   
            /** 向request中存入message信息 */  
            request.setAttribute("message", "用户注册成功!!!");   
        } catch (Exception ex) {   
            ex.printStackTrace();   
            request.setAttribute("message", "用户注册失败!!!");   
        }   
  
        /** 获取跳转的地址并返回ActionForward */  
        return mapping.findForward("message");   
    }

}
