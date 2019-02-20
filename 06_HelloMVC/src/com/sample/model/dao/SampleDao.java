package com.sample.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.rclass.member.model.vo.Member;

public class SampleDao {

	private Properties prop=new Properties();
	
	public SampleDao() {
		try {
			String fileName=SampleDao.class
					.getResource("/sql/sample/sample1.properties")
					.getPath();
			prop.load(new FileReader(fileName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
				result=rs.getInt("cnt");
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
	
	
	public List<Member> selectList(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		String sql=prop.getProperty("selectList");
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Member m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setEmail(rs.getString("email"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				list.add(m);
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






