package com.apa.hospital.info;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalMyPageInfoDTO;
import com.apa.model.UserDTO;
import com.apa.repository.HospitalMyPageDAO;
import com.apa.repository.UserDAO;

@WebServlet("/hospital/info/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		
		//1.
		//String id = req.getSession().getAttribute("id").toString();
		
		//2.
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		HospitalMyPageInfoDTO dto = dao.get(id); //회원 정보
		
		String[] ssnArr = dto.getHospitalSsn().split("-");
		
		dto.setHospitalSSNs(ssnArr[0]);
		dto.setHospitalSSNm(ssnArr[1]);
		dto.setHospitalSSNe(ssnArr[2]);
		
		String[] telArr = dto.getHospitalTel().split("-");
		
		dto.setHospitalTels(telArr[0]);
		dto.setHospitalTelm(telArr[1]);
		dto.setHospitalTele(telArr[2]);
		
		//3.
		req.setAttribute("dto", dto);
		req.setAttribute("maskingSSN", ssnArr[2].charAt(0) + "******");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/info/view.jsp");
		dispatcher.forward(req, resp);
	}
}
