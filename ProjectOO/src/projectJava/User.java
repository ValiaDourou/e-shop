package projectJava;
public abstract class User {
	private String name;
	private String email;
	public User(String name,String email)
	{
		this.name=name;
		this.email=email;
	}
	public String getUserName()
	{
		return name;
	}
}
