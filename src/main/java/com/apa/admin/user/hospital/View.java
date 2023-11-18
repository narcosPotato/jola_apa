package com.apa.admin.user.hospital;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminHospitalDTO;
import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminHospitalDAO;
import com.apa.repository.AdminUserDAO;

@WebServlet("/admin/user/hospital/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String hospitalId = req.getParameter("hospitalId");
		
		AdminHospitalDAO dao = new AdminHospitalDAO();
		
		AdminHospitalDTO dto  = dao.detail(hospitalId);
		
		//System.out.println(dto);
		
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/hospital/view.jsp");
		dispatcher.forward(req, resp);
	}
}
