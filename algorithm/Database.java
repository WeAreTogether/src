package algorithm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBConnection;



public class Database {
	
	
	private static Connection conn=null;

	private Statement st;
	
	
	public Database() {
		try{
			conn = new DBConnection().getConnection();
			if(conn != null){
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getLecturersSize() throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT count(*) FROM algo.lecturer");
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrs = st.executeQuery(query);
		qrs.first();
		return qrs.getInt(1);
		
	}
	
	public int getClassSize() throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT count(*) FROM algo.classes");
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrs = st.executeQuery(query);
		qrs.first();
		return qrs.getInt(1);
		
	}
	
	public int getCoursesSize() throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT count(*) FROM algo.course");
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrs = st.executeQuery(query);
		qrs.first();
		return qrs.getInt(1);
		
	}
	
	public int getAcademicHours(int CourseId) throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT academicHours FROM algo.course where courseid=?");
		 PreparedStatement ab = conn.prepareStatement(query);
		 ab.setInt(1, CourseId);
		 qrs=ab.executeQuery();
			qrs.first();
		return qrs.getInt(1);
		
	}
}