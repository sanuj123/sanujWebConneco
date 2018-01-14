package com.WebConneco.registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.WebConneco.user.UserDAO;

/**
 * Servlet implementation class VerifyEmailServlet
 */
@WebServlet("/verifyEmail")
public class VerifyEmailServlet extends HttpServlet {
	
	String message = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int studentId = Integer.parseInt(request.getParameter("userid"));
		String hash = request.getParameter("hash");
		String scope = request.getParameter("scope");
		String message = null;
		
		UserDAO dao = new UserDAO();
		
		try {
			if(dao.verifyEmailHash(studentId, hash) && scope.equals("activation")) {
				
				dao.updateStatus(studentId);
				
				message = "Email verified successfully. Account was activated. Please login your account";
			}
			else {
				message = "Invalid Authorization";
			}
						
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
