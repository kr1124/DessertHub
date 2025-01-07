package com.desserthub.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desserthub.conn.ConnUtil;

public class BoardDAO {

    //글 목록 DB에서 불러오기
    public List<BoardDto> selectBoardList() {
        //임시로 임의 값 넣음 
        int start = 1;
        int end = 10;

        Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardDto> articleList = null;
		
		try {
			con = ConnUtil.getConnection();
			
			//String sql = "select * from board order by num desc"; //기존 쿼리문
			String sql = "select * from ("
					+ "select rownum rnum, num, writer, email, subject, "
					+ "pass, readcount, ref, step, depth, regdate, content, ip from ("
					+ "select * from board order by ref desc, step asc)) "
					+ "where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList<BoardDto>(end - start + 1);
				do {
					BoardDto article = new BoardDto();
                    article.setPost_id(rs.getString("post_id"));
                    article.setPost_num(rs.getInt("post_num"));
                    article.setUser_id(rs.getString("user_id"));
                    article.setUser_nn(rs.getString("user_nn"));
                    article.setPost_title(rs.getString("post_title"));
                    article.setPost_body(rs.getString("post_body"));
                    article.setPost_img(rs.getString("post_img"));
                    article.setPost_liked(rs.getInt("post_liked"));
                    article.setPost_comment(rs.getInt("post_comment"));
                    article.setPost_writeday(rs.getTimestamp("post_writeday"));
					
					articleList.add(article);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {try {rs.close();} catch(SQLException e) {}}
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {}}
			if(con != null) {try {con.close();} catch(SQLException e) {}}
		}
		
		return articleList;
    }
}
