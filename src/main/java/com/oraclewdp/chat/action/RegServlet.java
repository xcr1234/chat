package com.oraclewdp.chat.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewdp.chat.commons.User;
import com.oraclewdp.chat.service.UserService;

public class RegServlet extends HttpServlet {


	private UserService userService = new UserService();
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5791644378034910270L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		String nick = request.getParameter("nick");
		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		User user = userService.getUser(usr);
		if(user!=null){
			out.println("注册失败，用户名已经存在！<br/>");
			out.println("<a href=\"index.jsp\">返回首页</a>");
			return;
			
		}
		user = new User();
		user.setUsr(usr);
		user.setNick(nick);
		user.setPwd(pwd);
		userService.addUser(user);
		out.println("注册成功，请登录！<br/>");
		out.println("<a href=\"index.jsp\">返回首页</a>");
		return;
	}

}
