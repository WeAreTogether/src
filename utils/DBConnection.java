package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	   String driver="com.mysql.jdbc.Driver";
	      String url="jdbc:mysql://127.0.0.1:3306/algo";
	      String user="root";
	      String password="password";
	      Connection con=null;
	
	      public DBConnection() {
			// TODO Auto-generated constructor stub
		}
	      public Connection getConnection(){
	    	  try {
				Class.forName(driver);
				con=DriverManager.getConnection(url,user,password);
				//System.out.println("connection received");
				return con;
			   } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			         } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    	  return null;
	    	  
	      }
	      
	      
	      public void closeConnection(){
	    	  
	    	  try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
}
