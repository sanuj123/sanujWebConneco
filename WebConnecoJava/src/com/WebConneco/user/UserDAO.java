package com.WebConneco.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.WebConneco.connection.JDBCConnection;
import com.WebConneco.registration.RegistrationSQL;

public class UserDAO {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public boolean doExist(String email) throws ClassNotFoundException, SQLException {
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(RegistrationSQL.IS_EXIST_EMAIL);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst() ) {
				return false;
			}
			else {
				return true;
			}
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	
	public boolean doExist(String email, String password) throws ClassNotFoundException, SQLException {
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(UserSQL.DO_USER_EXIST);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst() ) {
				return false;
			}
			else {
				return true;
			}
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	
	public String isActive(String email) throws ClassNotFoundException, SQLException {
		String status = "registered";
		try {
			
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(RegistrationSQL.IS_EMAIL_ACTIVE);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				 status = rs.getString(1);
			}
			return status;
			
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	
	public boolean register(UserDTO dto) throws ClassNotFoundException, SQLException {
		
		try {
			con = JDBCConnection.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(RegistrationSQL.REGISTER_USER);
			
			ps.setString(1, dto.getUserName());
			ps.setString(2, dto.getEmailId());
			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getEmailHash());
			ps.setString(5, dto.getStatus());
			ps.setBlob(6, dto.getInputStream());
			
			if(ps.executeUpdate()>0) {
				con.commit();
				return true;
			}
			else {
				con.rollback();
				return false;
			}
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
		
		
	}
	
	public int findStudentId(String email) throws ClassNotFoundException, SQLException {
		int studentId = 0;
		
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(RegistrationSQL.FIND_STUDENT_ID);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				studentId = rs.getInt(1);
			}
			return studentId;
			
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	
	public boolean verifyEmailHash(int studentId, String emailHash) throws ClassNotFoundException, SQLException {
		
		try {
		con = JDBCConnection.getConnection();
		ps = con.prepareStatement(RegistrationSQL.VERIFY_EMAIL_HASH);
		ps.setInt(1,studentId);
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			if(emailHash.equals(rs.getString(1))) {
				return true;
			}
			
		}
		return false;
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	
	public void updateStatus(int studentId) throws ClassNotFoundException, SQLException {
		
		try {
		con = JDBCConnection.getConnection();
		ps = con.prepareStatement(RegistrationSQL.UPDATE_STATUS);
		ps.setString(1,"active");
		ps.setInt(2, studentId);
		
		rs = ps.executeQuery();
		
		
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	public boolean updatePassword(int studentId, String newPassword) throws ClassNotFoundException, SQLException {
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(UserSQL.RESET_PASSWORD);
			ps.setString(1, newPassword);
			ps.setInt(1, studentId);
			
			int i = ps.executeUpdate();
			if(i>0)
				return true;
			return false;
			
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	public String getOldPassword(int studentId) throws ClassNotFoundException, SQLException {
		
		String oldPassword = null;
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(UserSQL.GET_OLD_PASSWORD);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				oldPassword = rs.getString(1);
			}
			return oldPassword;
			
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
public UserDTO getUserDetails(String email, String password) throws ClassNotFoundException, SQLException {
		
		UserDTO dto = null;
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(UserSQL.LOG_IN_USER);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				 dto = new UserDTO(rs.getInt(1),rs.getString(2));
			}
			return dto;
			
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	public UserDTO getUserDetails2(String email, String password) throws ClassNotFoundException, SQLException {
		
		UserDTO dto = null;
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(UserSQL.DO_USER_EXIST);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				 dto = new UserDTO(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(7));
			}
			return dto;
			
		}finally {
			JDBCConnection.closeConnection(rs, ps, con);
		}
	}
	

}