package com.computerstore.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	// create data source pool
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	// create ThreadLocal , to bind Connection
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>(); 
	
	// get data source
	public static DataSource getDataSource(){
		return cpds;
	}
	
	// get connection
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			conn = cpds.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	
	//start transaction
	public static void startTransaction() throws SQLException{
		getConnection().setAutoCommit(false);
	}
	
	//commit transaction
	public static void commitAndRelease() throws SQLException{
		Connection conn = getConnection();
		conn.commit();
		conn.close();
		tl.remove();
	}
	
	//rollback
	public static void rollback() throws SQLException{
		getConnection().rollback();
	}
	
	//release the resources
	public static  void closeResource(Statement st,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResource(Connection conn,Statement st,ResultSet rs){
		closeResource(st, rs);
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
