package projectJava;
public class Owner extends User{
	private boolean isAdmin=true;
	private static String ownerEmail,ownerName;
	public Owner(String name,String email,boolean isAdmin)
	{
		super(name,email);
		ownerEmail=email;
		ownerName=name;
		this.isAdmin=isAdmin;
	}
	public static String getEmail()
	{ 
		return ownerEmail;
	}
	public static String getName()
	{
		return ownerName;
	}
}
