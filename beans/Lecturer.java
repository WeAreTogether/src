package beans;

public class Lecturer {
public Lecturer() {
	// TODO Auto-generated constructor stub
}
private int lecid;
private String accronym_in_tt;
private String lecname;
private int allocated_course;
private int year_of_joining;
private String specialization;
private String experience;
public int getLecid() {
	return lecid;
}
public void setLecid(int lecid) {
	this.lecid = lecid;
}
public String getAccronym_in_tt() {
	return accronym_in_tt;
}
public void setAccronym_in_tt(String accronym_in_tt) {
	this.accronym_in_tt = accronym_in_tt;
}
public String getLecname() {
	return lecname;
}
public void setLecname(String lecname) {
	this.lecname = lecname;
}
public int getAllocated_course() {
	return allocated_course;
}
public void setAllocated_course(int allocated_course) {
	this.allocated_course = allocated_course;
}
public int getYear_of_joining() {
	return year_of_joining;
}
public void setYear_of_joining(int year_of_joining) {
	this.year_of_joining = year_of_joining;
}
public String getSpecialization() {
	return specialization;
}
public void setSpecialization(String specialization) {
	this.specialization = specialization;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}

}
