package com.sunbeam.servletes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

@WebServlet("/editcand")
public class EditCandidateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get the candidate id (from req)
		String idstr = req.getParameter("id");
		int id = Integer.parseInt(idstr);
		//find the candidate details in db
		Candidate c=null;
		try (CandidateDao candDao = new CandidateDaoImpl()){
			c = candDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	//  show candidate details in html form - for editing
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Edit Candidate</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h3>Online Voting</h3>");
				out.println("<form method='post' action='?'>");
				out.printf("Id: <input type='text' name='id' value='%d' readonly/><br/><br/>\n", c.getId());
				out.printf("Name: <input type='text' name='name' value='%s'/><br/><br/>\n", c.getName());
				out.printf("Party: <input type='text' name='party' value='%s'/><br/><br/>\n", c.getParty());
				out.printf("Votes: <input type='text' name='votes' value='%d' readonly/><br/><br/>\n", c.getVotes());
				out.println("<input type='submit' value='Update Candidate'/>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
		}
	