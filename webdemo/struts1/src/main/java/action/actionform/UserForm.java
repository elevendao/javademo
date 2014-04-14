package action.actionform;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	/**  
     * 封装用户提交的参数信息,此属性名称一定要与jsp视图中   
     * 表单提交的参数名称一致  
     */  
    private String username;   
    private String userpass;   
    private String useremail;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
}
