package com.rclass.notice.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.close;

import com.rclass.notice.model.vo.Notice;

public class NoticeDao {
	private Properties prop=new Properties();
	
	public NoticeDao() {
		try {
			String fileName=NoticeDao.class
					.getResource("/sql/notice/notice-query.properties")
					.getPath();
			prop.load(new FileReader(fileName));			
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public int deleteNotice(Connection conn, Notice n)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteNotice");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, n.getNoticeNo());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	
	
	public int updateNotice(Connection conn, Notice n)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateNotice");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getFilePath());
			pstmt.setInt(5, n.getNoticeNo());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int selectSeq(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectSeq");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int insertNotice(Connection conn, Notice n)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertNotice");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getFilePath());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public Notice selectOne(Connection conn, int no)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		/*Notice n=new Notice();*/
		Notice n=null;
		String sql=prop.getProperty("selectOne");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				n=new Notice();
				n.setNoticeNo(rs.getInt("notice_no"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setNoticeContent(rs.getString("notice_content"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setFilePath(rs.getString("filepath"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return n;
	}
	
	
	public int selectCount(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt(1);
			}
		}
		catch(SQLException e)
		{e.printStackTrace();}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	

	
	public List<Notice> selectList(Connection conn, int cPage, int numPerPage)
	{
		//DB정보를 가져오는 객체
		//1. connection, PreparedStatement, String sql,
		//Resultset(select)/int(insert,update,delete)
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> list=new ArrayList();
		String sql=prop.getProperty("selectList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Notice n=new Notice();
				n.setNoticeNo(rs.getInt("notice_no"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setNoticeContent(rs.getString("notice_content"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setFilePath(rs.getString("filepath"));
				list.add(n);				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;		
	}
	
	
}
