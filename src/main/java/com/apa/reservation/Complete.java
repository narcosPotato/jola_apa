package com.apa.reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.InsertRegisterDTO;
import com.apa.repository.ReservationDAO;

@WebServlet("/reservation/complete.do")
public class Complete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/complete.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		//진료예약
		String rezhospitaid = req.getParameter("rezhospitaid");	//병원 아이디
		String rezuserseq = session.getAttribute("seq").toString();	//회원 아이디
		String rezchildseq = req.getParameter("rezchildseq");	//자녀번호
		String reztype = req.getParameter("reztype");		//진료방식
		String rezdocseq = req.getParameter("rezdocseq");	//의사번호
		String rezgotime = req.getParameter("rezgotime");	//예약일
		String rezregtime = req.getParameter("rezregtime");	//신청일
		String reztext = req.getParameter("reztext");		//상세증상
		//약국
		String rezdrugtype = req.getParameter("rezdrugtype");//약수령 타입
		String rezdrugtime = req.getParameter("rezdrugtime");//약받는 시간
		
		Date now = new Date();
		rezdrugtime = now.getYear()+1900+"-"+(now.getMonth()+1)+"-"+now.getDate()+" "+rezdrugtime+":00";

		InsertRegisterDTO dto = new InsertRegisterDTO();
		dto.setHospitalId(rezhospitaid);
		dto.setUserSeq(rezuserseq);
		dto.setChildSeq(rezchildseq);
		dto.setMediWay(reztype);
		dto.setDoctorSeq(rezdocseq);
		dto.setTreatementDate(rezgotime);
		dto.setRegdate(rezregtime);
		dto.setSymptom(reztext);
		
		ReservationDAO dao = new ReservationDAO();

		
		if (reztype.equals("건강검진")) {
			int result = dao.insercheck(dto);
			if (result == 1) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/complete.jsp");
				dispatcher.forward(req, resp);
			}else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
			}
		} else if (reztype.equals("drug")) {
			System.out.println(dto.getHospitalId());
			System.out.println(dto.getTreatementDate());
			int result = dao.insertdurg(dto);
			if (result == 1) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/complete.jsp");
				dispatcher.forward(req, resp);
			}else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
			}
		} else if (rezdrugtype != null) {
			int result = dao.insertDispense(rezdrugtype, rezdrugtime);
			if (result == 1 ) {
				int result2 = dao.inserRegister(dto);
				if (result2 == 1) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/complete.jsp");
					dispatcher.forward(req, resp);
				}else {
					PrintWriter writer = resp.getWriter();
					writer.print("<script>alert('failed');history.back();</script>");
					writer.close();
				}
			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
			}
		} else {
			
			int result2 = dao.inserRegisterNoDrug(dto);
			
			if (result2 == 1) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/complete.jsp");
				dispatcher.forward(req, resp);
			}else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
			}
		}
		
	}
}
