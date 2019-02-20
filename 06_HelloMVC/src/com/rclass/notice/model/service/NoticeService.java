package com.rclass.notice.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.rclass.notice.model.dao.NoticeDao;
import com.rclass.notice.model.vo.Notice;
import static common.JDBCTemplate.close;

public class NoticeService {
	
	private NoticeDao dao=new NoticeDao();

	public int deleteNotice(Notice n)
	{
		Connection conn=getConnection();
		int result=dao.deleteNotice(conn,n);
		if(result>0)
		{
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	
	public int updateNotice(Notice n)
	{
		Connection conn=getConnection();
		int result=dao.updateNotice(conn, n);
		if(result>0)
		{
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int insertNotice(Notice n)
	{
		Connection conn=getConnection();
		int result=dao.insertNotice(conn, n);
		if(result>0)
		{
			commit(conn);
			result=dao.selectSeq(conn);
		}
		else {rollback(conn);}
		close(conn);
		
		return result;		
	}
	
	
	public Notice selectOne(int no)
	{
		Connection conn=getConnection();
		Notice n=dao.selectOne(conn,no);
		close(conn);
		return n;
	}
	
	
	public List<Notice> selectList(int cPage, int numPerPage)
	{
		Connection conn=getConnection();
		List<Notice> list=dao.selectList(conn,cPage, numPerPage);
		close(conn);
		return list;		
	}
	
	public int selectCount()
	{
		Connection conn=getConnection();
		int result=dao.selectCount(conn);
		close(conn);
		return result;
		
	}
	
	
}
