package com.sunbeam.servletes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;

public class VoteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequst(req,resp);	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			processRequst(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void processRequst(HttpServletRequest req, HttpServletResponse resp) throws Exception, IOException {
		String candidateId =req.getParameter("candidate");
		int id =Integer.parseInt(candidateId);
		try (CandidateDao candDao = new CandidateDaoImpl()){
			candDao.incrementVote(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
				out.println("<head>");
			out.println("<title>Voted</title>");
				out.println("</head>");
				out.println("<body>");
			out.println("Your vote is registerd successfully. <br/><br/>");
				out.println("<a href='logout'>Sign Out</a>");
				out.println("</body>");
				out.println("</html>");
		
		
	}
	
}
