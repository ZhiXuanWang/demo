package com.df.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBUtil {

	private static Connection conn=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs = null;
	private static String URL="jdbc:oracle:thin:@192.168.10.32:2016:sxlib";
	private static String USER="TT_TEST1";
	private static String PASSWORD="TT";
	public static void getConn(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int update(String sql,Object[] o){
		int k = 0;
		try {
			handleData(sql, o);
			k= ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
		
	}
	private static void handleData(String sql, Object[] o) throws SQLException {
		getConn();
		ps = conn.prepareStatement(sql);
		for(int i=0;i<o.length;i++){
		ps.setObject(i+1,o[i]);
		}
	}
	
	public static ResultSet query(String sql,Object[] o){
		try {
			handleData(sql, o);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static void close(){
			try {
				if(rs!=null)
				rs.close();
				if(ps!=null) 
					ps.close();
				if(conn!=null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	}

}
