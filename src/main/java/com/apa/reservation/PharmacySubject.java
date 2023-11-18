package com.apa.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.MediQuestionDTO;
import com.apa.model.OpenTimeDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.ReservationDAO;

@WebServlet("/reservation/pharmacysubject.do")
public class PharmacySubject extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		
		
			ReservationDAO dao = new ReservationDAO();
			
			Date now = new Date();
			
			OpenTimeDTO timedto = dao.time2(id);
			timedto.setCloseTime(timedto.getCloseTime().substring(11));
			timedto.setOpenTime(timedto.getOpenTime().substring(11));
			
			
			req.setAttribute("nowtime", now.getHours()+":"+now.getMinutes()+":00");
			req.setAttribute("timedto", timedto);
			
			req.setAttribute("id", id);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/pharmacysubject.jsp");
			dispatcher.forward(req, resp);		
		
	}
}
