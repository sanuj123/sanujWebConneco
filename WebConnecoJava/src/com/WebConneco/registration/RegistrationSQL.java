package com.WebConneco.registration;

public interface RegistrationSQL {
  
	String REGISTER_USER = "INSERT INTO student_login(`user_name`, `email_id`, `password`, `last_login`, `email_verification_hash`, `status`, `photo`) VALUES(?,?,?,?,now(),?,?,?);";
	
	String IS_EXIST_EMAIL = "select * from student_login where email_id=?;";
	
	String IS_EMAIL_ACTIVE = "select status from student_login where email_id=?;";
	
	String FIND_STUDENT_ID = "select student_id from student_login where email_id=?;";
	
	String VERIFY_EMAIL_HASH = "select email_verification_hash from student_login where student_id=?;";
	
	String UPDATE_STATUS = "update student_login set status=? where student_id=?";
}
