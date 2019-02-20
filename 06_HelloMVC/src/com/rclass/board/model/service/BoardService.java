package com.rclass.board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.rclass.board.model.dao.BoardDao;
import com.rclass.board.model.vo.Board;
import com.rclass.board.model.vo.BoardComment;

public class BoardService {

	private BoardDao dao=new BoardDao();

	public Board selectOne(int boardNo, boolean hasRead) {
		Connection conn=getConnection();
		Board b=dao.selectOne(conn, boardNo);

		if(b!=null&&!hasRead) {

			//		조회수 부분 리턴값이 있을때 증가하게끔
			if(b!=null) {
				int result=dao.increReadCount(conn, boardNo);
				if(result>0) {
					commit(conn);
				}
				else {
					rollback(conn);
				}
			}
		}
		close(conn);
		return b;
	}




	public int selectCount()
	{
		Connection conn=getConnection();
		int result=dao.selectCount(conn);
		close(conn);
		return result;
	}

	public List<Board> selectList(int cPage, int numPerpage)
	{
		Connection conn=getConnection();
		List<Board> list=dao.selectList(conn,cPage,numPerpage);
		close(conn);
		return list;

	}

	public int insertBoard(Board b)
	{
		Connection conn=getConnection();
		int result=dao.insertBoard(conn,b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
	public int insertBoardComment(BoardComment comment) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn,comment);
		if(result>0) {
			commit(conn);
		}
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
	public List<BoardComment> selectCommentAll(int no){
		Connection conn=getConnection();
		List<BoardComment> list=dao.selectCommentAll(conn, no);
		close(conn);
		return list;
	}
	
//	댓글 삭제
	public int deleteComment(int delNo) {
		Connection conn=getConnection();
		int result=dao.deleteComment(conn, delNo);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	
	
	




}






