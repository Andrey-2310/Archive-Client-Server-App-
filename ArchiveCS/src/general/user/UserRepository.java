package general.user;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import Server.ServerStart;
import general.serialization.SerializeManager;
import general.user.UserDescription;
import general.user.UserDescription.Roles;
import general.Response;
import general.Request.Requests;


public class UserRepository{
	/** The Hashtable containes Key = users login, Value = object user */
	public static Hashtable<String, UserDescription> ht = new Hashtable<String, UserDescription>();
	
	public static void addUser(UserDescription user){
	ht.put(user.getLogin(), user);
	new general.serialization.SerializeManager<Hashtable<String, UserDescription>>().save(ht, getXmlFileName());
	}
	
	
	public static UserDescription checkByLoginAndPassword(String login, long password) {
		UserDescription user = ht.get(login);
		if(user==null) {
			System.out.println("ѕользовател€ с логином: "+ login+" не существует");
			return user;
		}
		if(user.getPassword()!=password){
			System.out.println("Ќеверный пароль дл€ пользовател€ с логином: "+ login);
			return null;
		}
		return user;
	}
	
	
	public static void setupUsers(){
		addUser(new UserDescription(Roles.ADMIN, "admin", "admin", "Andrey"));
		addUser(new UserDescription(Roles.USER, "user1", "user1", "Roman"));
		addUser(new UserDescription(Roles.USER, "user2", "user2", "Dmitriy"));
		addUser(new UserDescription(Roles.USER, "user3", "user3", "Tatsiana"));
		addUser(new UserDescription(Roles.GUEST, "guest1", "guest1", "Mihail"));
		addUser(new UserDescription(Roles.GUEST, "guest2", "guest2", "Timofey"));
		new general.serialization.SerializeManager<Hashtable<String, UserDescription>>().save(getHt(), getXmlFileName());
	}
	
	public static String getXmlFileName(){
		return new String("Users");
	}
	
	public static Hashtable<String, UserDescription>getHt(){
		return ht;
	}
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public static Response getAll() {
		Enumeration<UserDescription> files = ht.elements();
		Vector<UserDescription> users = new Vector<UserDescription>();
		while (files.hasMoreElements()) {
			users.addElement(files.nextElement());
		}
		ServerStart.loggerServer.info(new String("Request to get all users is served"));
		Response responce = new Response(Requests.SHOW_USERS, null, null, true);
		responce.setUsers(users);
		return responce;
	}

	/**
	 * Change users.
	 *
	 * @param users
	 *            the users
	 * @return the response
	 */
	public static Response changeUsers(Vector<UserDescription> users) {
		ht.get(users.get(0).getLogin()).setRole(users.get(0).getRole());
		Response responce = new Response(Requests.CHANGE_USERS, null, null, true);
		return responce;
	}
	/**
	 * Load data.
	 */
	public static void loadData() {
		ht = new SerializeManager<Hashtable<String, UserDescription>>().load(ht, getXmlFileName());
	}
}
