package com.oraclewdp.chat.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oraclewdp.chat.commons.User;

public class UserService {

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public User getUser(String usr) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite::resource:users.db");
			ps = conn.prepareStatement("select * from user where usr = ?");
			ps.setString(1, usr);
			rs = ps.executeQuery();
			if (rs.next()) {
				String pwd = rs.getString("pwd");
				String nick = rs.getString("nick");
				User user = new User();
				user.setUsr(usr);
				user.setPwd(pwd);
				user.setNick(nick);
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

			}

		}
	}

	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite::resource:users.db");
			ps = conn.prepareStatement("insert into user(usr,pwd,nick) values(?,?,?)");
			ps.setString(1, user.getUsr());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getNick());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {

			}

		}

	}

	public void removeUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite::resource:users.db");
			ps = conn.prepareStatement("delete user where usr = ?");
			ps.setString(1, user.getUsr());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {

			}

		}

	}

}
