package com.oraclewdp.chat;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.oraclewdp.chat.commons.ConcurrentSet;
import com.oraclewdp.chat.commons.FieldNames;
import com.oraclewdp.chat.commons.User;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext context = event.getServletContext();
		Map<String,User> map = new ConcurrentHashMap<String,User>(); //表示所有的用户
		context.setAttribute(FieldNames.allUsers, map);
		Set<User> set = new ConcurrentSet<User>();
		context.setAttribute(FieldNames.allLogined, set);
		

	}

}
