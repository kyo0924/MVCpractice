package com.rclass.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.rclass.admin.model.dao.AdminDao;
import com.rclass.member.model.vo.Member;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;

public class AdminService {
	
	private AdminDao dao=new AdminDao();
		
	
	public int selectMemberCount()
	{
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn);
		close(conn);
		return result;		
	}
	
	public List<Member> selectSearchMember(String type, String key, int cPage,int numPerPage)
	{
		Connection conn=getConnection();
		List<Member> list=dao.selectSearchMember(conn, type, key, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	
	public int selectMemberCount(String type,String key)
	{
		Connection conn=getConnection();
		int result=dao.selectSearchCount(conn, type, key);
		close(conn);
		return result;
	}
	
	public List<Member> selectMemberList(int cPage, int numPerPage)
	{
		Connection conn=getConnection();
		List<Member> list=dao.selectMemberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Member> selectSearchMember(String type,String key)
	{
		Connection conn=getConnection();
		List<Member> list=dao.selectSearchMember(conn,type,key);
		close(conn);
		return list;
		
	}

}





