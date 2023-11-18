package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminReportDTO;

public class AdminReportDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminReportDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<AdminReportDTO> list(HashMap<String, String> map) {
		
        int begin = Integer.parseInt(map.get("begin"));
        int end = Integer.parseInt(map.get("end"));
		
	    try {
	        String sql = String.format("SELECT * FROM (SELECT v.*, rownum as rnum FROM vwReportList v) where rnum between %s and %s"
								, map.get("begin")
								, map.get("end"));
	        
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);
	        
	        ArrayList<AdminReportDTO> list = new ArrayList<AdminReportDTO>();
	        
	        while (rs.next()) {
	        	
	        	AdminReportDTO dto = new AdminReportDTO();
	            
	        	dto.setReportUserId(rs.getString("reportUserId"));
	        	dto.setReportUserName(rs.getString("reportUserName"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setReportType(rs.getString("reportType"));
	            dto.setIsReportState(rs.getString("isReportState"));
	            dto.setReportCount(rs.getString("reportCount"));
	            dto.setCommunityTitle(rs.getString("communityTitle"));
	            dto.setCommunityContent(rs.getString("communityContent"));
	            dto.setCommunityLikeCount(rs.getString("communityLikeCount"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
		return null;
	}
	
	public int getTotalCount() {
		
		try {
			
			String sql = "SELECT count(*) as cnt FROM vwReportList";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public AdminReportDTO detail(String userSeq) {
		
		try {
	         
	         String sql = "SELECT * FROM tblReportBox WHERE userSeq = ?";
	         
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, userSeq);
	         
	         rs = pstat.executeQuery();
	         
	         AdminReportDTO dto = new AdminReportDTO();
	         	         
	         if(rs.next()) {
	        	 
	            dto.setUserSeq(rs.getString("userSeq"));
	        	 
	            return dto;
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return null;
	}

	public int approval(AdminReportDTO dto) {

		try {

			String sql = "update tblReportBox set isReportState = '승인' where userSeq = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getUserSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int decline(AdminReportDTO dto) {
		
		try {

			String sql = "update tblReportBox set isReportState = '거절' where userSeq = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getUserSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}





}
