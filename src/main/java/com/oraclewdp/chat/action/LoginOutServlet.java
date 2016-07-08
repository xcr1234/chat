package com.oraclewdp.chat.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewdp.chat.commons.FieldNames;

public class LoginOutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6046008512000161484L;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute(FieldNames.sessionLogin);
		response.sendRedirect("index.jsp");
	}

}
