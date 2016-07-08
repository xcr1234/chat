package com.oraclewdp.chat.commons;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1715573982245651034L;
	
	private int type;
    
    private String content;
    
    private String target;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
