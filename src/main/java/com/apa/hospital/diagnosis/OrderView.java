package com.apa.hospital.diagnosis;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.hospital.DiagnosisRgstDTO;
import com.apa.model.hospital.DgnsMediHistoryDTO;
import com.apa.repository.hospital.DiagnosisDAO;

@WebServlet("/hospital/diagnosis/order-view.do")
public class OrderView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mediSeq = req.getParameter("mediSeq");

		DiagnosisDAO dao = new DiagnosisDAO();

		//예약 정보
		DiagnosisRgstDTO dto = dao.getOrderDetail(mediSeq);
		
		if (dto.getSymptom() != null) {

			// 진단서
			DgnsMediHistoryDTO writeDto = dao.getWriteDiagnosis(mediSeq);

			if (writeDto != null && writeDto.getMediContent() != null) {

				// 개행문자처리
				String content = writeDto.getMediContent();
				content = content.replace("\r\n", "<br>");
				writeDto.setMediContent(content);
			}

			req.setAttribute("writeDto", writeDto); // 진료내역서 dto
		}

		if (dto.getSymptom() != null) {
			// 개행문자처리
			String symptom = dto.getSymptom();
			symptom = symptom.replace("\r\n", "<br>");
			dto.setSymptom(symptom);
		}

		// 해당 의사 이름
		String doctorName = dao.getDoctorName(mediSeq);

		req.setAttribute("dto", dto); // 예약정보

		req.setAttribute("doctorName", doctorName); // 해당 진료 의사 이름


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/diagnosis/order-view.jsp");
		dispatcher.forward(req, resp);
	}
}
