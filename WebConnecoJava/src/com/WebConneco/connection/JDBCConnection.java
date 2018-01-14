package com.WebConneco.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface JDBCConnection {

	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		ResourceBundle rb = ResourceBundle.getBundle("config");
		Class.forName(rb.getString("drivername"));
		Connection con = DriverManager.getConnection(rb.getString("dburl"),rb.getString("userid"),rb.getString("password"));
		System.out.println(con.getCatalog());
		return con;
	}

	public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
		if(rs != null) {
			rs.close();
		}
		if(ps != null) {
			ps.close();
		}
		if(con != null) {
			con.close();
		}
		
	}

}
