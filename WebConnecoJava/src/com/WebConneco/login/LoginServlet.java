package com.WebConneco.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.WebConneco.user.UserDAO;
import com.WebConneco.user.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		UserDAO dao = new UserDAO();
		boolean exist = false;
		UserDTO dto = new UserDTO();
		
			
			try {
				dto = dao.getUserDetails2(email, pass);
				System.out.println(dto.toString());
				if(BCrypt.checkpw(pass, dto.getPassword())) {
					exist = true;
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(exist) {
				System.out.println("session");
				HttpSession session = request.getSession(true);
				session.setAttribute("studentId", dto.getStudentId());
				session.setAttribute("userName", dto.getUserName());
				System.out.println("welcome");
				response.sendRedirect("welcome.html");
				
				return;
			}
			else {
				response.sendRedirect("login.html");
				System.out.println("not valid");
			}
			
		
		
		
	}


}
