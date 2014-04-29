package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import actions.Gettingsubjects;
import utils.DBConnection;

/**
 * Servlet implementation class registerservlet
 */
@WebServlet("/registerservlet")
public class registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstname=request.getParameter("username");
		String lastname=request.getParameter("lastname");
		String special=request.getParameter("special");
		String year=request.getParameter("year");
		String position=request.getParameter("position");
		String experience=request.getParameter("experience");
		String assign=request.getParameter("assign");
		int id=0;
		String accro="/"+firstname.charAt(0)+lastname.charAt(0);
		String lecname=position+" "+firstname+" "+lastname;
		Connection conn=new DBConnection().getConnection();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select max(lecid) from lecturer");
			while(rs.next()){
				id=rs.getInt(1);
			}
			
			id++;
			
			Map<String, Integer> map=new Gettingsubjects().getallsubjectskey();
			int assign_id=map.get(assign);
			PreparedStatement pre1=conn.prepareStatement("insert into lecturer values (?,?,?,?,?,?,?)");
			pre1.setInt(1, id);
			pre1.setString(2, accro);
			pre1.setString(3, lecname);
			pre1.setInt(4, assign_id);
			pre1.setInt(5, Integer.parseInt(year));
			
			pre1.setString(6, special);
			pre1.setString(7, experience);
			pre1.executeUpdate();
			
			
			
			response.sendRedirect("message.jsp");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	private void alert(String string) {
		// TODO Auto-generated method stub
		
	}

}
