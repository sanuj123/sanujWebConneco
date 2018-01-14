package com.WebConneco.user;

public interface UserSQL {

	String DO_USER_EXIST = "select * from student_login where email_id = ?;";
	
	String RESET_PASSWORD = "update student_login set password = ? where student_id = ?;";
	
	String GET_OLD_PASSWORD = "select password from student_login where student_id = ?;";
	
	String LOG_IN_USER = "select student_id, user_name from student_login where email_id = ? AND password = ?;";

}
