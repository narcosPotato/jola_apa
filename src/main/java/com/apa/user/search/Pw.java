package com.apa.user.search;

import com.apa.model.LoginHospitalDTO;
import com.apa.model.LoginPharmacyDTO;
import com.apa.model.LoginUserDTO;
import com.apa.repository.LoginHospitalDAO;
import com.apa.repository.LoginPharmacyDAO;
import com.apa.repository.LoginUserDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/search/pw.do")
public class Pw extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/search/pw.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");

		String checked = req.getParameter("checked");
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");

		String tel = tel1 + "-" + tel2 + "-" + tel3;

		if (checked.equals("1")) {
			LoginUserDAO dao = new LoginUserDAO();
			LoginUserDTO dto = new LoginUserDTO();

			dto.setUserName(name);
			dto.setUserTel(tel);
			dto.setUserId(id);
			dto.setUserPw(pw);

			LoginUserDTO user = dao.getUserPw(dto);

			LoginUserDTO resultSearch = dao.searchPw(dto);
			LoginUserDTO resultUpdate = dao.updatePw(dto);

			if(user != null && dto.getUserPw().equals(user.getUserPw())){
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('이전 비밀번호와 같은 비밀번호 입니다.');history.back();</script>");
				writer.close();
				return;
			}

			if (resultSearch != null && resultUpdate !=null) {
				req.getSession().setAttribute("seq", resultSearch.getUserSeq());
				req.getSession().setAttribute("name", name);
				req.getSession().setAttribute("tel", tel);
				req.getSession().setAttribute("lv", "1");
				req.getSession().setAttribute("id", id);
				req.getSession().setAttribute("pw", pw);

				resp.sendRedirect("/apa/user/login.do");
			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
				writer.close();
			}

		} else if (checked.equals("2")) {

			LoginHospitalDAO dao = new LoginHospitalDAO();
			LoginHospitalDTO dto = new LoginHospitalDTO();

			dto.setHospitalName(name);
			dto.setHospitalTel(tel);
			dto.setHospitalId(id);
			dto.setHospitalPw(pw);

			LoginHospitalDTO resultSearch = dao.searchPw(dto);
			LoginHospitalDTO resultUpdate = dao.updatePw(dto);


			if (resultSearch != null && resultUpdate !=null) {
				req.getSession().setAttribute("name", name);
				req.getSession().setAttribute("tel", tel);
				req.getSession().setAttribute("lv", "2");
				req.getSession().setAttribute("id", id);
				req.getSession().setAttribute("pw", pw);


				resp.sendRedirect("/apa/user/login.do");

			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
				writer.close();

			}

		} else if (checked.equals("3")) {
			LoginPharmacyDAO dao = new LoginPharmacyDAO();
			LoginPharmacyDTO dto = new LoginPharmacyDTO();

			dto.setPharmacyName(name);
			dto.setPharmacyTel(tel);
			dto.setPharmacyId(id);
			dto.setPharmacyPw(pw);

			LoginPharmacyDTO resultSearch = dao.searchPw(dto);
			LoginPharmacyDTO resultUpdate = dao.updatePw(dto);

			if (resultSearch != null && resultUpdate !=null) {
				req.getSession().setAttribute("name", name);
				req.getSession().setAttribute("id", id);
				req.getSession().setAttribute("pw", pw);
				req.getSession().setAttribute("tel", tel);
				req.getSession().setAttribute("lv", "3");

				resp.sendRedirect("/apa/user/login.do");

			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
				writer.close();




			}

		}

		req.getSession().invalidate();
	}
}
