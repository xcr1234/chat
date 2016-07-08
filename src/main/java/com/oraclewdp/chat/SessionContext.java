package com.oraclewdp.chat;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.oraclewdp.chat.comet.ChatCometServlet;
import com.oraclewdp.chat.commons.FieldNames;
import com.oraclewdp.chat.commons.Message;
import com.oraclewdp.chat.commons.User;

public class SessionContext implements HttpSessionAttributeListener {

	@SuppressWarnings("unchecked")
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
        if (name.equals(FieldNames.sessionLogin)) {

            User user = (User) event.getValue();
            if (user != null) {
                ServletContext servletContext = event.getSession().getServletContext();
                Set<User> allLogin = (Set<User>) servletContext.getAttribute(FieldNames.allLogined);
                allLogin.add(user);

                System.out.println("login in:" + user.getUsr());
                Message msg = new Message();
                msg.setType(1);
                msg.setContent(user.getNick());
                msg.setTarget(user.getUsr());
                ChatCometServlet.write(msg);
            }

        }
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
		String name = event.getName();
        if (name.equals(FieldNames.sessionLogin)) {
            User user = (User) event.getValue();
            if (user != null) {
                ServletContext servletContext = event.getSession().getServletContext();
                Set<User> allLogin = (Set<User>) servletContext.getAttribute(FieldNames.allLogined);
                allLogin.remove(user);

                System.out.println("login out：" + user.getUsr());
                Message msg = new Message();
                msg.setType(2);
                msg.setContent(user.getNick());
                msg.setTarget(user.getUsr());
                ChatCometServlet.write(msg);
            }
        }

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		

	}

}
