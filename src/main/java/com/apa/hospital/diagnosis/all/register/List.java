package com.apa.hospital.diagnosis.all.register;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.hospital.DiagnosisRgstDTO;
import com.apa.repository.hospital.DiagnosisDAO;

@WebServlet("/hospital/diagnosis/all/register/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// list.do 호출

		// 페이징
		// list.do > 1페이지 간주
		// list.do?page=1

		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한 페이지에서 출력할 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 페이징 시작 위치
		int end = 0; // 페이징 끝 위치
		int n = 0;
		int loop = 0;
		int blockSize = 10; // 한번에 보여줄 페이지 개수

		String page = req.getParameter("page");

		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}

		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		// 1. DB 작업 > select
		// 2. 반환 > jsp 호출

		HttpSession session = req.getSession();

		String hospitalId = session.getAttribute("id").toString();

		HashMap<String, String> map = new HashMap<>();

		map.put("hospitalId", hospitalId);

		map.put("begin", begin + "");
		map.put("end", end + "");

		// 1.
		DiagnosisDAO dao = new DiagnosisDAO();
		ArrayList<DiagnosisRgstDTO> list = dao.getAllRegisterList(map);

		// 1.5. 데이터 가공
		// 긴 상세증상 줄이기
		for (DiagnosisRgstDTO dto : list) {
			String symptom = dto.getSymptom();
			if (symptom != null && symptom.length() > 25) {
				symptom = symptom.substring(0, 25) + "...";
			}
			dto.setSymptom(symptom);
		}

		totalCount = dao.getAllRegisterCount(hospitalId);

		totalPage = (int) Math.ceil((double) totalCount / pageSize);

		StringBuilder sb = new StringBuilder();

		loop = 1; // 루프 변수(10바퀴)

		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 출력 페이지 번호

		// 이전 10페이지
		if (n == 1) {
			sb.append(" <a href='#!';>[이전 페이지]</a>&nbsp;&nbsp;");
		} else {
			sb.append(String.format(" <a href='/apa/hospital/diagnosis/all/register/list.do?page=%d';>[이전 페이지]</a>&nbsp;&nbsp;",
					n - 1));
		}

		while (!(loop > blockSize || n > totalPage)) {

			if (n == nowPage) {
				sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a>&nbsp;&nbsp;", n));
			} else {
				sb.append(
						String.format(" <a href='/apa/hospital/diagnosis/all/register/list.do?page=%d'>%d</a>&nbsp;&nbsp;", n, n));
			}

			loop++;
			n++;
		}

		// 다음 10페이지
		if (n > totalPage) {
			sb.append(" <a href='#!';>[다음 페이지]</a> ");
		} else {
			sb.append(String.format(" <a href='/apa/hospital/diagnosis/all/register/list.do?page=%d';>[다음 페이지]</a> ",
					n));
		}


		// 2.
		req.setAttribute("list", list);
		req.setAttribute("map", map);

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);

		req.setAttribute("pagebar", sb.toString());

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/hospital/diagnosis/all/register/list.jsp");
		dispatcher.forward(req, resp);
	}

}