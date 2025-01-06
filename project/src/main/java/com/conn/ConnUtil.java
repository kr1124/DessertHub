package com.conn;

import java.sql.*;
import javax.sql.*;

import javax.naming.*;

public class ConnUtil {
	private static DataSource ds;

	static {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myOracle");
			
			System.out.println("데이터베이스 연결 성공");
		} catch(NamingException ne) {
			System.out.println("커넥션 생성 실패");
			ne.printStackTrace();
		} catch(Exception e) {
			System.out.println("데이터베이스 연결 실패");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	//어느 DB에, 어떤 쿼리문을, 상세 내용 앞부분, 상세내용 뒷부분, ? 채워넣기
    public static DataWrapper sql_execute(String db_name, String sql_type, String sql_part1, String sql_part2, String[] plist) {        
		Connection con = null;
		PreparedStatement pstmt = null;
        
        DataWrapper dw = null;
        int result = 1;
		ResultSet rs = null;

        String sql = sql_type + sql_part1 + db_name + sql_part2;

		try {
			con = ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql);
            for(int i = 0; i < plist.length; i++) {
			    pstmt.setString(i + 1, plist[i]);
            }
			
            if(sql_type == "select") {
			    rs = pstmt.executeQuery();

                if(!rs.next()) {
                    result = 0;
                }
                dw = new DataWrapper(result, rs);
            } else if(sql_type == "insert" || sql_type == "update" || sql_type == "delete") {
			    result = pstmt.executeUpdate();
                dw = new DataWrapper(result);
            }
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {try {rs.close();} catch(SQLException e) {}}
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {}}
			if(con != null) {try {con.close();} catch(SQLException e) {}}
		}
		
		return dw;
    }
	
	public static DataWrapper sql_execute(String db_name, String sql_type, String sql_part1, String sql_part2) {
		return sql_execute(db_name, sql_type, sql_part1, sql_part2, null);
	}
}
