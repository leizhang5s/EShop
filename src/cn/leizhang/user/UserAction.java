package cn.leizhang.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.leizhang.user.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//接收验证码
	private String checkcode ;
	private User user=new User();
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	@Override
	public User getModel() {
		
		return user;
	}
	@InputConfig(resultName="registInput")
	public String regist()
	{
		String checkcode2=(String) ActionContext.getContext().getSession().get("checkcode");
		if(checkcode2.equalsIgnoreCase(checkcode))
		{
			userService.regist(user);
			this.addActionMessage("注册成功!请去邮箱激活!");
			return "registSuccess";
		}
		else
		{
			this.addActionError("验证码错误");
			return "registInput";
		}
		
	}
	//前台登陆功能
	@InputConfig(resultName="loginInput")
	public String login()
	{
		User existUser=userService.existUser(user);
		if(existUser!=null)
		{
			ActionContext.getContext().getSession().put("existUser",existUser);
			return "loginSuccess";
		}
		else
		{
			this.addActionError("用户名或密码错误或用户未激活!");
			return "loginInput";
		}
		
	}
	//控制页面跳转
	public String  loginPage()
	{
		return "loginPageSuccess";
	}
	
	public String registPage()
	{
		return "registPageSuccess";
	}
	//退出功能
	public String quit()
	{
		ActionContext.getContext().getSession().remove("existUser");
		return "quitSuccess";
	}
	//前台:注册AJAX校验用户名.
	//验证用户名是否存在的代码
	public String checkUserName() throws IOException{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(user.getUsername()==null||user.getUsername().equals(""))
		{
			response.getWriter().print("<font color='green'>用户名不能为空</font>");
		}
		else{
			User existUser=userService.findByUserName(user.getUsername());
			if(existUser == null){
				response.getWriter().print("<font color='green'>用户名可以使用</font>");
			}
			else{
				// 用户名已经存在
				response.getWriter().print("<font color='red'>用户名已经存在</font>");
			}
			
		}
			
			
		
		return NONE;
	}
	
}
