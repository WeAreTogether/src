package beans;

public class Building {

	public Building() {
		// TODO Auto-generated constructor stub
	}
	private int buildingid;
	private String buildingName;
	public int getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(int buildingid) {
		this.buildingid = buildingid;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	@Override
	public String toString() {
		return "Building [buildingid=" + buildingid + ", buildingName="
				+ buildingName + "\n";
	}
	
	
}
