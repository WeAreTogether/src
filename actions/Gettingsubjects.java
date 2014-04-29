package actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.DBConnection;

public class Gettingsubjects {

	public Gettingsubjects() {
		// TODO Auto-generated constructor stub
	}
	public List<String> getallsubjects(){
		List<String> allsub=new ArrayList<String>();
		String sub;
		Connection conn=new DBConnection().getConnection();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs= stmt.executeQuery("select * from course");
			while(rs.next()){
				sub=new String();
				sub=rs.getString(3);
				allsub.add(sub);
			}
			return allsub;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String,Integer> getallsubjectskey(){
		Map<String, Integer> map=new HashMap<String,Integer>();
		String sub;
		Integer i;
		Connection conn=new DBConnection().getConnection();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs= stmt.executeQuery("select * from course");
			while(rs.next()){
				sub=new String();
				sub=rs.getString(3);
				i=rs.getInt(1);
                 map.put(sub, i);
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
