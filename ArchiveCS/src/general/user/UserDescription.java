package general.user;


public class UserDescription {
	/**
	 * The Enum Roles.
	 */
	public static enum Roles {
		
 /** The administrator. */
	ADMIN, 
 /** The user. */
	USER, 
 /** The guest. */
	GUEST
	};

	/** The role. */
	private Roles role;
	
	/** The login. */
	private String login;
	
	/** The password. */
	private long password;
	
	/** The name. */
	private String name;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public long getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(long password) {
		this.password = password;
	}
	
	
	public UserDescription(Roles role, String login, String password, String name) {
		this.setRole(role);
		this.login=login;
		this.password=password.hashCode();
		this.setName(name);
	}

	/**
	 * @return the role
	 */
	public Roles getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Roles role) {
		this.role = role;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
