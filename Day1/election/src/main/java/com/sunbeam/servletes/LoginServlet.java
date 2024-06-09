package com.sunbeam.servletes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		try(UserDao userDao = new UserDaoImpl()) {
			User user = userDao.findByEmail(email);
			if(user != null && user.getPassword().equals(passwd)) {
				// login successful
//				Cookie c=new Cookie ("uname", user.getFirstName());
//				c.setMaxAge(3600);//3600 seconds =1 hours
//				resp.addCookie(c);
				
				HttpSession Session= req.getSession();
				Session.setAttribute("curser", user);
				
				System.out.println("Login Successful: " + user);
				if(user.getRole().equals("voter")) {//voter login
					//String reUrl = resp.encodeRedirectURL("candlist");
					//resp.sendRedirect(reUrl);
								
										//String reUrl = resp.encodeURL("candlist");
										//RequestDispatcher rd = req.getRequestDispatcher( reUrl);
										//resp.sendRedirect("candlist");
										
										
					
					RequestDispatcher rd =req.getRequestDispatcher("candlist");
					rd.forward(req, resp);
					
					//requestDispatcher rd= req.getRequestDispatcher ("candlist");
					
				}
				else {//admin login 
					//String reUrl = resp.encodeRedirectURL("result");
//					resp.sendRedirect(reUrl);
//										//resp.sendRedirect("result");
										//resp.sendRedirect("result");
					RequestDispatcher rd =req.getRequestDispatcher("candlist");
					rd.forward(req, resp);
				}
			}
			else {
				// login failed
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login Failed</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Invalid email or password. <br/><br/>");
				out.println("<a href='index.html'>Login Again</a>");
				out.println("</body>");
				out.println("</html>");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
			 
		}
	}
}