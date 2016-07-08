package com.oraclewdp.chat.commons;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7340002871728709252L;
	
	private String usr;
	
	private String pwd;
	
	
	private String nick;


	public String getUsr() {
		return usr;
	}


	public void setUsr(String usr) {
		this.usr = usr;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usr == null) ? 0 : usr.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (usr == null) {
			if (other.usr != null)
				return false;
		} else if (!usr.equals(other.usr))
			return false;
		return true;
	}


}
