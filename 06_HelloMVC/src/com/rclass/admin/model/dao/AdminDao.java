package com.rclass.admin.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate.close;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.rclass.member.model.vo.Member;

public class AdminDao {
	private Properties prop=new Properties();
	
	public AdminDao(){
		try {
			String fileName=AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Member> selectSearchMember(Connection conn,String type, String key, int cPage,int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		String sql="";
		switch(type)
		{
			case "userId":sql=prop.getProperty("searchUserIdList");break;
			case "userName":sql=prop.getProperty("searchUserNameList");break;
			case "gender":sql=prop.getProperty("searchGenderList");break;
		}
		try {
			System.out.println(sql);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3,cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Member m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				list.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {close(rs);close(pstmt);}
		return list;
		
	}
	
	
	
	public int selectSearchCount(Connection conn, String type, String key)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="";
		switch(type)
		{
			case "userId":sql=prop.getProperty("searchUserIdCount");break;
			case "userName":sql=prop.getProperty("searchUserNameCount");break;
			case "gender":sql=prop.getProperty("searchGenderCount");break;
		}
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
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
		finally {close(rs);close(pstmt);}
		return result;
	}
	
	
	public int selectMemberCount(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberCount");
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
	
	
	
	public List<Member> selectSearchMember(Connection conn, String type, String key)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		String sql="";
		switch(type)
		{
			case "userId":sql=prop.getProperty("searchUserId");break;
			case "userName":sql=prop.getProperty("searchUserName");break;
			case "gender":sql=prop.getProperty("searchGender");break;
		}
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Member m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				list.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {close(rs);close(pstmt);}
		return list;
	}
	
	
	
	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectMemberList");
		List<Member> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage);
			
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Member m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				list.add(m);
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		finally {close(rs);close(pstmt);}
		return list;
	}
	
	
	
	
}



