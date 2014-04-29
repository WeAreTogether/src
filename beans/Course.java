package beans;

public class Course {
	public Course() {
		// TODO Auto-generated constructor stub
	}
	private int courseid;
	private String course_avbr;
	private String coursename;
	private int semester;
	private int faculty;
	private int studentno;
	private int academicHours;
	public int getAcademicHours() {
		return academicHours;
	}
	@Override
	public String toString() {
		return "Course [courseid=" + courseid + ", course_avbr=" + course_avbr
				+ ", coursename=" + coursename + ", semester=" + semester
				+ ", faculty=" + faculty + ", studentno=" + studentno
				+ ", academicHours=" + academicHours + "]";
	}
	public void setAcademicHours(int academicHours) {
		this.academicHours = academicHours;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCourse_avbr() {
		return course_avbr;
	}
	public void setCourse_avbr(String course_avbr) {
		this.course_avbr = course_avbr;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getFaculty() {
		return faculty;
	}
	public void setFaculty(int faculty) {
		this.faculty = faculty;
	}
	public int getStudentno() {
		return studentno;
	}
	public void setStudentno(int studentno) {
		this.studentno = studentno;
	}
	
	
	
}
