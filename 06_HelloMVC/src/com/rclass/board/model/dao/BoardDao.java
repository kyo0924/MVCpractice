package com.rclass.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.close;
import com.rclass.board.model.vo.Board;
import com.rclass.board.model.vo.BoardComment;

public class BoardDao {

	private Properties prop=new Properties();

	public BoardDao() {
		try {
			String filename=BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
			prop.load(new FileReader(filename));
		}
		catch(IOException e)
		{e.printStackTrace();}
	}

	public int insertBoard(Connection conn, Board b)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFileName());
			pstmt.setString(5, b.getBoardRenamedFileName());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{e.printStackTrace();}
		finally {
			close(pstmt);	
		}
		return result;
	}


	public int selectCount(Connection conn)
	{
		PreparedStatement pstmt=null;
		int result=0;
		ResultSet rs=null;
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


	public List<Board> selectList(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		String sql=prop.getProperty("selectList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Board b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardOriginalFileName(rs.getString("board_original_filename"));
				b.setBoardRenamedFileName(rs.getString("board_renamed_filename"));
				b.setReadCount(rs.getInt("board_readcount"));
				list.add(b);
			}
		}
		catch(SQLException e)
		{e.printStackTrace();}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return list;
	}



	public Board selectOne(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		String sql=prop.getProperty("selectOne");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardOriginalFileName(rs.getString("board_original_filename"));
				b.setBoardRenamedFileName(rs.getString("board_renamed_filename"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setReadCount(rs.getInt("board_readcount"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return b;

	}

	//조회수부분!
	public int increReadCount(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql=prop.getProperty("increReadCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}


	//	댓글 부분
	public int insertBoardComment(Connection conn, BoardComment comment) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql=prop.getProperty("insertBoardComment");
		System.out.println(sql+":"+comment);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBoardCommentLevel());
			pstmt.setString(2, comment.getBoardCommentWriter());
			pstmt.setString(3, comment.getBoardCommentContent());
			pstmt.setInt(4, comment.getBoardRef());
			//5번이 중요한데 여기에다가 null값을 넣어야함 숫자는 null이 없음 그래서 string으로 넣을겁니다
			pstmt.setString(5, comment.getBoardCommentRef()==0?null:String.valueOf(comment.getBoardCommentRef()));

			result=pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<BoardComment> selectCommentAll(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> list = new ArrayList();
		String sql=prop.getProperty("selectCommentAll");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardComment bc = new BoardComment();
				bc.setBoardCommentNo(rs.getInt("board_comment_no"));
				bc.setBoardCommentLevel(rs.getInt("board_comment_level"));
				bc.setBoardCommentWriter(rs.getString("board_comment_writer"));
				bc.setBoardCommentContent(rs.getString("board_comment_content"));
				bc.setBoardRef(rs.getInt("board_ref"));
				bc.setBoardCommentRef(rs.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rs.getDate("board_comment_date"));
				list.add(bc);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return list;
	}
	
	public int deleteComment(Connection conn, int delNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, delNo);
			result=pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}



}
