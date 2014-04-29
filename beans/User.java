package beans;

public class User {

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name
				+ ", auth_type=" + auth_type + "\n";
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	private int id;
	private String password;
	private String name;
	private String auth_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}
	
	
}
