package Server.User;

import java.util.Hashtable;

import Server.User.UserDescription.Roles;



public class UserRepository{
	/** The Hashtable containes Key = users login, Value = object user */
	public static Hashtable<String, UserDescription> ht = new Hashtable<String, UserDescription>();
	
	public static void addUser(UserDescription user){
	ht.put(user.getLogin(), user);
	new Serialization.SerializeManager<Hashtable<String, UserDescription>>().save(ht, getXmlFileName());
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
		addUser(new UserDescription(Roles.ADMINISTRATOR, "admin", "admin", "Andrey"));
		addUser(new UserDescription(Roles.USER, "user1", "user1", "Roman"));
		addUser(new UserDescription(Roles.USER, "user2", "user2", "Dmitriy"));
		addUser(new UserDescription(Roles.USER, "user3", "user3", "Tatsiana"));
		addUser(new UserDescription(Roles.GUEST, "guest1", "guest1", "Mihail"));
		addUser(new UserDescription(Roles.GUEST, "guest2", "guest2", "Timofey"));
		//new Serialization.SerializeManager<Hashtable<String, UserDescription>>().save(getHt(), getXmlFileName());
	}
	
	public static String getXmlFileName(){
		return new String("Users");
	}
	
	public static Hashtable<String, UserDescription>getHt(){
		return ht;
	}
}
