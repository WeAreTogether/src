package beans;

public class ClassRoom {
public ClassRoom() {
	// TODO Auto-generated constructor stub
}
private int classroomno;
private int floor;
private int capacity;
public int getClassroomno() {
	return classroomno;
}
public void setClassroomno(int classroomno) {
	this.classroomno = classroomno;
}
private int lab;
private boolean available;

public int getFloor() {
	return floor;
}
public void setFloor(int floor) {
	this.floor = floor;
}
public int getCapacity() {
	return capacity;
}
public void setCapacity(int capacity) {
	this.capacity = capacity;
}
public int getLab() {
	return lab;
}
public void setLab(int lab) {
	this.lab = lab;
}
public boolean isAvailable() {
	return available;
}
public void setAvailable(boolean available) {
	this.available = available;
}

}
