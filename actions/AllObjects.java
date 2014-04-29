package actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnection;
import beans.ClassRoom;
import beans.Course;
import beans.Lecturer;

public class AllObjects {
public AllObjects() {
	// TODO Auto-generated constructor stub
}

public List<ClassRoom> getclassrooms(){
	List<ClassRoom> classroom_list=new ArrayList<ClassRoom>();
	ClassRoom classroom;
	Connection conn=new DBConnection().getConnection();
	try {
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from classes");
		while(rs.next()){
			classroom=new ClassRoom();
			classroom.setClassroomno(rs.getInt(1));
			classroom.setFloor(rs.getInt(2));
			classroom.setCapacity(rs.getInt(3));
			classroom.setLab(rs.getInt(4));
			classroom.setAvailable(rs.getBoolean(5));
			classroom_list.add(classroom);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return classroom_list;
}
public List<Course> getcourses(){
	List<Course> course_list=new ArrayList<Course>();
	Course course;
	Connection conn=new DBConnection().getConnection();
	try {
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from course");
		while(rs.next()){
			course=new Course();
			course.setCourseid(rs.getInt(1));
			course.setCourse_avbr(rs.getString(2));
			course.setCoursename(rs.getString(3));
			course.setSemester(rs.getInt(4));
			course.setStudentno(rs.getInt(6));
			course.setFaculty(rs.getInt(5));
			course_list.add(course);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return course_list;
}

public List<Lecturer> getlecturer(){
	List<Lecturer> lecturer_list=new ArrayList<Lecturer>();
	Lecturer lecturer;
	Connection conn=new DBConnection().getConnection();
	try {
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from lecturer");
		while(rs.next()){
            lecturer=new Lecturer();
            lecturer.setLecid(rs.getInt(1));
            lecturer.setAccronym_in_tt(rs.getString(2));
            lecturer.setLecname(rs.getString(3));
            lecturer.setAllocated_course(rs.getInt(4));
            lecturer.setYear_of_joining(rs.getInt(5));
            lecturer.setSpecialization(rs.getString(6));
            lecturer.setExperience(rs.getString(7));
            lecturer_list.add(lecturer);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lecturer_list;
}
}
