package beans;

public class Campus {
@Override
	public String toString() {
		return "Campus [capmpusid=" + capmpusid + ", campusname=" + campusname
				+ ", campus=" + campus + "\n";
	}
public Campus() {
	// TODO Auto-generated constructor stub
}

private int capmpusid;
private String campusname;
private Campus campus;

public Campus getCampus() {
	return campus;
}
public void setCampus(Campus campus) {
	this.campus = campus;
}
public int getCapmpusid() {
	return capmpusid;
}
public void setCapmpusid(int capmpusid) {
	this.capmpusid = capmpusid;
}
public String getCampusname() {
	return campusname;
}
public void setCampusname(String campusname) {
	this.campusname = campusname;
}


}
