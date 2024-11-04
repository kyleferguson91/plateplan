import java.util.ArrayList;
import java.util.List;

public class User {
	
	
	static List<User> users = new ArrayList();
	//a list on each user object to hold recipes/favs?

	
	
	private String username;
	private String password;
	private String email;
	
	public User(String username, String password, String email)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		
		users.add(this);
	}
	
	public String getUsername()
	{
		return this.username;
	}

	public String getEmail()
	{
		return this.email;
		
	}
	
	public static User getUserByUsername(String username)
	{
		for (int i = 0; i<users.size(); i++)
		{
			if (users.get(i).getUsername().equals(username))
			{
				return users.get(i);
			}
		}
		return null;
	}
	
}
