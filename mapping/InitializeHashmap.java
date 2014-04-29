package mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import utils.DBConnection;
import beans.Building;
import beans.Campus;
import beans.Course;
import beans.Lecturer;
import beans.ClassRoom;
import beans.User;

public class InitializeHashmap {

	Connection conn = new DBConnection().getConnection();

	public InitializeHashmap() {
		// TODO Auto-generated constructor stub
	}

	public HashMap<Integer, Building> initbuild() {

		HashMap<Integer, Building> build = new HashMap<Integer, Building>();
		Building building;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from building");
			while (rs.next()) {
				building = new Building();
				building.setBuildingid(rs.getInt(1));
				building.setBuildingName(rs.getString(2));
				build.put(rs.getInt(1), building);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return build;
	}

	public HashMap<Integer, Lecturer> initlecturer() {

		HashMap<Integer, Lecturer> lect = new HashMap<Integer, Lecturer>();
		Lecturer lecturer;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from lecturer");
			while (rs.next()) {
				lecturer = new Lecturer();
				lecturer.setLecid(rs.getInt(1));
				lecturer.setAccronym_in_tt(rs.getString(2));
				lecturer.setLecname(rs.getString(3));
				lecturer.setAllocated_course(rs.getInt(4));
				lecturer.setYear_of_joining(rs.getInt(5));
				lecturer.setSpecialization(rs.getString(6));
				lecturer.setExperience(rs.getString(7));
				lect.put(rs.getInt(1), lecturer);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lect;
	}

	public HashMap<Integer, ClassRoom> initClassRoom() {

		HashMap<Integer, ClassRoom> clas = new HashMap<Integer, ClassRoom>();
		ClassRoom cls;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from classes");
			while (rs.next()) {
				cls = new ClassRoom();
				cls.setClassroomno(rs.getInt(1));
				cls.setFloor(rs.getInt(2));
				cls.setCapacity(rs.getInt(3));
				cls.setLab(rs.getInt(4));
				cls.setAvailable(rs.getBoolean(5));
				clas.put(rs.getInt(1), cls);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clas;
	}

	/*public HashMap<Integer, Lecturer> initlecturer() {

		HashMap<Integer, Lecturer> faculty = new HashMap<Integer, Lecturer>();
		Lecturer lect;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from lecturer");
			while (rs.next()) {
				lect = new Lecturer();
				lect.setLecid(rs.getInt(1));
				lect.setAccronym_in_tt(rs.getString(2));
				lect.setLecname(rs.getString(3));
				lect.setAllocated_course(rs.getInt(4));
				lect.setYear_of_joining(rs.getInt(5));
				lect.setSpecialization(rs.getString(6));
				lect.setExperience(rs.getString(7));
				faculty.put(rs.getInt(1), lect);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return faculty;
	}
*/
	public HashMap<Integer, Course> initcourse() {

		HashMap<Integer, Course> course = new HashMap<Integer, Course>();
		Course co;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from course");
			while (rs.next()) {
				co = new Course();
				co.setCourseid(rs.getInt(1));
				co.setCourse_avbr(rs.getString(2));
				co.setCoursename(rs.getString(3));
				co.setSemester(rs.getInt(4));
				co.setFaculty(rs.getInt(5));
				co.setStudentno(rs.getInt(6));
				co.setAcademicHours(rs.getInt(7));
				course.put(rs.getInt(1), co);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}

	public HashMap<Integer, Campus> initcampus() {

		HashMap<Integer, Campus> campus = new HashMap<Integer, Campus>();
		Campus cam;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from campus");
			while (rs.next()) {
				cam = new Campus();
				cam.setCapmpusid(rs.getInt(1));
				cam.setCampusname(rs.getString(2));
				campus.put(rs.getInt(1), cam);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return campus;
	}

	public HashMap<Integer, User> inituser() {

		HashMap<Integer, User> user = new HashMap<Integer, User>();
		User u;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login");
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setAuth_type(rs.getString(4));
				user.put(rs.getInt(1), u);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
