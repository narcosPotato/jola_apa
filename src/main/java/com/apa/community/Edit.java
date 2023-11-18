package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/community/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				//- view.do > 수정하기 버튼 클릭 > edit.do?seq=5
				//1. 데이터 가져오기(seq)
				//2. DB 작업 > select
				//3. 결과 + JSP 호출하기
				
		/*
				 * if(Auth.check(req,resp)) { return; }
				 */
				
				
				//1.
				String seq = req.getParameter("seq");
				
				//2.
				communityDAO dao = new communityDAO();
				
				//수정할 번호를 내가 알고 있으니까 제목과 내용이 뭐였는지 알려줘!
				communityDTO dto = dao.get(seq);
				
				//수정하기 제목에서 생기는 스타일태그 오류 수정하기
				String subject = dto.getSubject();
				
				subject = subject.replace("\"","&quot;");
				dto.setSubject(subject);
				
				//3. JSP에게 데이터가 넘어감
				req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		//EditOk.java
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 피드백
				
		HttpSession session = req.getSession(); //아이디를 여기서 꺼냄
		req.setCharacterEncoding("utf-8");		
				
		//1.
		//req.setCharacterEncoding("UTF-8");
				
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");  //수정할 글번호
				
		//2.
		communityDAO dao = new communityDAO();
				
		communityDTO dto = new communityDTO();
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setId(session.getAttribute("id").toString());
		dto.setCommunitySeq(seq); //수정할 글번호 
				
		int result = dao.edit(dto);
				
		//3.
		if (result == 1) {
			
			//수정하기를 누르면 상세보기로 넘어감.
			resp.sendRedirect("/apa/community/view.do?seq=" + seq);
					
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
	
}
