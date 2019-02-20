package com.sample.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.rclass.member.model.vo.Member;
import com.sample.model.dao.SampleDao;
public class SampleService {

	public int selectCount()
	{
		Connection conn=getConnection();
		int result=new SampleDao().selectCount(conn);
		close(conn);
		return result;
	}
	
	public List<Member> selectList(int cPage, int numPerPage)
	{
		
		Connection conn=getConnection();
		List<Member> list=new SampleDao().selectList(conn,cPage,numPerPage);
		close(conn);
		return list;
		
	}
	
	
	
}






