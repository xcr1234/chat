package com.oraclewdp.chat.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewdp.chat.commons.FieldNames;
import com.oraclewdp.chat.commons.User;
import com.oraclewdp.chat.service.UserService;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9139761721598733633L;
	
	private UserService userService = new UserService();
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		
		
		User user = userService.getUser(usr);
		if(user==null){
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("登录失败，用户不存在！<br/>");
			out.println("<a href=\"index.jsp\">返回首页</a>");
			return;
			
		}
		if(!user.getPwd().equals(pwd)){
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("登录失败，密码不正确！<br/>");
			out.println("<a href=\"index.jsp\">返回首页</a>");
			return;
		}
		request.getSession().setAttribute(FieldNames.sessionLogin, user);
		response.sendRedirect("chat.jsp");
		
		
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
