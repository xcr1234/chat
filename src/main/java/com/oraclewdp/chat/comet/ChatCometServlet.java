package com.oraclewdp.chat.comet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.oraclewdp.chat.commons.FieldNames;
import com.oraclewdp.chat.commons.Message;
import com.oraclewdp.chat.commons.User;

public class ChatCometServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1032474339729547649L;

	public static final Queue<AsyncContext> ASYNC_CONTEXT_QUEUE = new ConcurrentLinkedQueue<AsyncContext>();

	/**
	 * 消息队列
	 */
	private static final BlockingQueue<String> MESSAGE_QUEUE = new LinkedBlockingQueue<String>();

	private boolean run;

	private Thread thread;
	
	

	public ChatCometServlet() {
		run = true;
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (run) {
					String message = null;
					try {
						message = MESSAGE_QUEUE.take();
						for (AsyncContext ac : ASYNC_CONTEXT_QUEUE) {

							if (accept(ac, message)) {
								try {
									PrintWriter acWriter = ac.getResponse().getWriter();
									acWriter.println(htmlEscape(message));
									acWriter.flush();

								} catch (IOException ex) {
									ex.printStackTrace();
									ASYNC_CONTEXT_QUEUE.remove(ac);
								}
							}

						}
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

	}
	
	@Override
	public void init() throws ServletException {
		thread.start();
	}

	private boolean accept(AsyncContext ac, String message) {

		Message msg = JSON.parseObject(message, Message.class);
		if (msg.getType() == 4) {
			String target = msg.getTarget();
			HttpServletRequest request = (HttpServletRequest) ac.getRequest();
			User user = (User) request.getSession().getAttribute(FieldNames.sessionLogin);
			return user != null && user.getUsr().equals(target);
		}

		return true;
	}

	private String htmlEscape(String message) {
		try {
			String base = Base64.encodeBase64String(message.getBytes("utf-8"));
			return "<script type='text/javascript'>\nwindow.parent.update(\"" + base + "\");</script>\n";
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
	}
	
	public static void write(String message){
        try {
            MESSAGE_QUEUE.put(message);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public static void write(Object object){
       
        write(JSON.toJSONString(object));
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (!request.isAsyncSupported()) {
            throw new ServletException("你的Web服务器不支持异步Servlet!");//一般是tomcat版本太低了的原因
        }
    	
    	//指定页面不缓存
    	response.setDateHeader("Expires", -1);//IE
        response.setHeader("Cache-Control", "private");
        response.setHeader("Pragma", "no-cache");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        
        PrintWriter writer = response.getWriter(); 
        // for IE 
        writer.println("<!-- Comet is a programming technique that enables web servers to send data to the client without having any need for the client  to request it. -->\n"); 
        writer.flush(); 
        
        final AsyncContext ac = request.startAsync(request, response);
        ac.setTimeout(600000L);
        ac.addListener(new AsyncListener() {

            @Override
            public void onComplete(AsyncEvent ae) throws IOException {
                ASYNC_CONTEXT_QUEUE.remove(ac);
            }

            @Override
            public void onTimeout(AsyncEvent ae) throws IOException {
                ASYNC_CONTEXT_QUEUE.remove(ac);
            }

            @Override
            public void onError(AsyncEvent ae) throws IOException {
                ASYNC_CONTEXT_QUEUE.remove(ac);
            }

            @Override
            public void onStartAsync(AsyncEvent ae) throws IOException {

            }
        });
        ASYNC_CONTEXT_QUEUE.add(ac);
        
        
        
    }

}
