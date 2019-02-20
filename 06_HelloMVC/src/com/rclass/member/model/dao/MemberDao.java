package com.rclass.member.model.dao;

import java.io.FileReader;
import java.security.KeyStore.ProtectionParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static common.JDBCTemplate.close;

import com.rclass.member.model.vo.Member;

public class MemberDao {

	private Properties prop=new Properties();
	
	public MemberDao(){
		try {
			String fileName=MemberDao.class.getResource("./member-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public int updatePassword(Connection conn, Member m)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updatePassword");
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getUserId());
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
	
	
	public int deleteMember(Connection conn, String userId)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	
	
	public int updateMember(Connection conn, Member m)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateMember");
		try {
			pstmt=conn.prepareStatement(sql);
			/*pstmt.setString(1, m.getPassword());*/
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getGender());
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getHobby());
			pstmt.setString(8, m.getUserId());
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
	
	
		
	public Member selectOne(Connection conn, Member m)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectOne");
		Member result=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=new Member();
				result.setUserId(rs.getString("userId"));
				result.setPassword(rs.getString("password"));
				result.setUserName(rs.getString("username"));
				result.setGender(rs.getString("gender"));
				result.setAge(rs.getInt("age"));
				result.setEmail(rs.getString("email"));
				result.setPhone(rs.getString("phone"));
				result.setAddress(rs.getString("address"));
				result.setHobby(rs.getString("hobby"));
				result.setEnrollDate(rs.getDate("enrolldate"));
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
	
	
	public int insertMember(Connection conn, Member m)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertMember");
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
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
	
	
	
	
	
}
