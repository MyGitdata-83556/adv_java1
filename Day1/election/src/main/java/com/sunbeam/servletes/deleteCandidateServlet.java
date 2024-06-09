package com.sunbeam.servletes;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

public class deleteCandidateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String  idStr =req.getParameter("id");
    int id = Integer.parseInt(idStr);
    try (CandidateDao candDao = new CandidateDaoImpl()){
    	
    	int count =candDao.deleteById(id);
    	RequestDispatcher rd = req.getRequestDispatcher("result");
    	rd.forward(req, resp);
		
	} catch (Exception e) {
		e.printStackTrace();
    throw new ServletException(e);
    
	}

	

	}
	}
