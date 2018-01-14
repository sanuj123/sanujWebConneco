package com.WebConneco.registration;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

import com.WebConneco.mailapp.SendMail;
import com.WebConneco.user.UserDAO;
import com.WebConneco.user.UserDTO;

/**
 * Servlet implementation class RegistrationServlet
 */

@WebServlet("/register")
@MultipartConfig(maxFileSize = 16177215) // Max Size of Image = 16Mb.
public class RegistrationServlet extends HttpServlet {
	boolean isEmailExist;
	String message;
	String accountStatus;
	boolean registerUser;
	ResourceBundle rb = ResourceBundle.getBundle("config");
	InputStream inputStream = null;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password");
		String password = BCrypt.hashpw(password1,BCrypt.gensalt());
		String emailHash = BCrypt.hashpw(email, BCrypt.gensalt());
		Part filePart = request.getPart("photo");
				
		UserDAO dao = new UserDAO();
						
		try {
			isEmailExist = dao.doExist(email);	
			
			if(isEmailExist) {
				
				accountStatus = dao.isActive(email);
				if(accountStatus == "active") {
					message = "Account related to this Email Id already exists. Please login through your existing account";
				}
				else{
					message = "This Email ID already registerd. If your account hasn't been activated, please check your Email account"
							+ "for Verification Link";
				}
			}
			else{
				String status = "registered";
				inputStream = filePart.getInputStream();
				UserDTO dto = new UserDTO(userName, email, password, emailHash, status, inputStream);
				
				registerUser = dao.register(dto);
				
				String cookEmail = emailHash;
				String cookPass = password;
				
				Cookie cookieEmail = new Cookie("ma_enter_em", cookEmail);
				Cookie cookiePass = new Cookie("ma_enter_ps", cookPass);
				
				cookieEmail.setMaxAge(365*24*60*60);
				cookiePass.setMaxAge(365*24*60*60);
				
				response.addCookie(cookieEmail);
				response.addCookie(cookiePass);
				
				if(!registerUser) {
					message = "An error occured. Please try Again later.";
				}
				else {
					int studentId = dao.findStudentId(email);
					
					message = "An activation link is sent to your email account for verfication of your account.";
					
					String link = rb.getString("registrationLink")+"?scope=activation&userid="+studentId+"&hash="+emailHash;
					StringBuilder bodyText = new StringBuilder();
					bodyText.append("<div>")
					        .append("  Dear User<br/><br/>")
					        .append("  Thank you for registration. Your mail ("+email+") is under verification<br/>")
					        .append("  Please click <a href=\""+link+"\">here</a> or open below link in browser<br/>")
					        .append("  <a href=\""+link+"\">"+link+"</a>")
					        .append("  <br/><br/>")
					        .append("  Thanks,<br/>")
					        .append("  Abroad Manager Team")
					        .append("</div>");
					
					String bodyMessage = bodyText.toString();
					
					SendMail.send(email,bodyMessage);
					
					PrintWriter pw = response.getWriter();
					pw.write("mail sent, check your mail");
					pw.close();
					       
				}
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
