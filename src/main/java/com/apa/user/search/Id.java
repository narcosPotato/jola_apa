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

@WebServlet("/user/search/id.do")
public class Id extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/search/id.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=utf-8");

            String checked = req.getParameter("checked");
            String name = req.getParameter("name");
            String email = req.getParameter("email");

            String tel1 = req.getParameter("tel1");
            String tel2 = req.getParameter("tel2");
            String tel3 = req.getParameter("tel3");

            String tel = tel1 + "-" + tel2 + "-" + tel3;

            if (checked.equals("1")) {
                LoginUserDAO dao = new LoginUserDAO();
                LoginUserDTO dto = new LoginUserDTO();

                dto.setUserName(name);
                dto.setUserEmail(email);
                dto.setUserTel(tel);

                LoginUserDTO result = dao.searchId(dto);

                if (result != null) {
                    req.getSession().setAttribute("seq", result.getUserSeq());
                    req.getSession().setAttribute("name", name);
                    req.getSession().setAttribute("tel", tel);
                    req.getSession().setAttribute("lv", "1");
                    req.getSession().setAttribute("email", email);
                    req.getSession().setAttribute("id", result.getUserId());

                    resp.sendRedirect("/apa/user/search/searchidresult.do");
                } else {
                    PrintWriter writer = resp.getWriter();
                    writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
                    writer.close();
                }
            } else if (checked.equals("2")) {

                LoginHospitalDAO dao = new LoginHospitalDAO();
                LoginHospitalDTO dto = new LoginHospitalDTO();

                dto.setHospitalName(name);
                dto.setHospitalEmail(email);
                dto.setHospitalTel(tel);

                LoginHospitalDTO result = dao.searchId(dto);


                if (result != null) {
                    req.getSession().setAttribute("name", name);
                    req.getSession().setAttribute("tel", tel);
                    req.getSession().setAttribute("lv", "2");
                    req.getSession().setAttribute("email", email);
                    req.getSession().setAttribute("id", result.getHospitalId());
;
                    resp.sendRedirect("/apa/user/search/searchidresult.do");

                } else {
                    PrintWriter writer = resp.getWriter();
                    writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
                    writer.close();

                }

            } else if (checked.equals("3")) {
                LoginPharmacyDAO dao = new LoginPharmacyDAO();
                LoginPharmacyDTO dto = new LoginPharmacyDTO();

                dto.setPharmacyName(name);
                dto.setPharmacyEmail(email);
                dto.setPharmacyTel(tel);

                LoginPharmacyDTO result = dao.searchId(dto);

                if (result != null) {
                    req.getSession().setAttribute("name", name);
                    req.getSession().setAttribute("id", result.getPharmacyId());
                    req.getSession().setAttribute("tel", tel);
                    req.getSession().setAttribute("lv", "3");
                    req.getSession().setAttribute("email", email);

                    resp.sendRedirect("/apa/user/search/searchidresult.do");

                } else {
                    PrintWriter writer = resp.getWriter();
                    writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
                    writer.close();




            }
        }
    }
}