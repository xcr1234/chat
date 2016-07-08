package com.oraclewdp.chat.comet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewdp.chat.commons.FieldNames;
import com.oraclewdp.chat.commons.Message;
import com.oraclewdp.chat.commons.User;

public class SendMessageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6454394126133575283L;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(FieldNames.sessionLogin);
        
        String target =request.getParameter("target");
        String content = request.getParameter("content");
        
        Message message = new Message();
        content = user.getUsr()+"说："+content;
        if(target.equals("")){
            message.setType(3);
            message.setContent(content);
        }else{
            message.setType(4);
            message.setTarget(target);
            message.setContent(content);
        }
        
        ChatCometServlet.write(message);
	}

}
