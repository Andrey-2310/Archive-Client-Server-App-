package general;

import java.util.Vector;

import general.person.Person;
import general.user.UserDescription;

/**
 * The Class Request. Used a data transport from client to server.
 */
public class Request {

	/**
	 * The Enum Requests.
	 */
	public static enum Requests {

		/** The show all. */
		SHOW_ALL,
		/** The find. */
		FIND,
		/** The add. */
		ADD,
		/** The edit. */
		EDIT,
		/** The delete. */
		DELETE,
		/** The exit. */
		EXIT,
		/** The show users. */
		SHOW_USERS,
		/** The change users. */
		CHANGE_USERS,
		/** The change parser*/
		CHANGE_PARSER
	};

	/** The request. */
	private Requests request = null;

	/** The search str. */
	private String searchStr = null;

	/** The personal data. */
	private Person personalData = null;

	/** The personal data new. */
	private Person personalDataNew = null;

	/** The users. */
	private Vector<UserDescription> users = new Vector<UserDescription>();

	/**
	 * Instantiates a new request.
	 */
	public Request() {
		super();
	}

	/**
	 * Instantiates a new request.
	 *
	 * @param request
	 *            the request
	 * @param searchStr
	 *            the search str
	 * @param personalData
	 *            the personal data
	 * @param personalDataNew
	 *            the personal data new
	 */
	public Request(Requests request, String searchStr, Person personalData, Person personalDataNew) {
		super();
		this.request = request;
		this.searchStr = searchStr;
		this.personalData = personalData;
		this.personalDataNew = personalDataNew;
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public Requests getRequest() {
		return request;
	}

	/**
	 * Sets the request.
	 *
	 * @param request
	 *            the new request
	 */
	public void setRequest(Requests request) {
		this.request = request;
	}

	/**
	 * Gets the personal data.
	 *
	 * @return the personal data
	 */
	public Person getPersonalData() {
		return personalData;
	}

	/**
	 * Gets the personal data new.
	 *
	 * @return the personal data new
	 */
	public Person getPersonalDataNew() {
		return personalDataNew;
	}

	/**
	 * Sets the personal data new.
	 *
	 * @param personalDataNew
	 *            the new personal data new
	 */
	public void setPersonalDataNew(Person personalDataNew) {
		this.personalDataNew = personalDataNew;
	}

	/**
	 * Sets the personal data.
	 *
	 * @param personalData
	 *            the new personal data
	 */
	public void setPersonalData(Person personalData) {
		this.personalData = personalData;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public Vector<UserDescription> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users
	 *            the new users
	 */
	public void setUsers(Vector<UserDescription> users) {
		this.users = users;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(UserDescription user) {
		this.users.clear();
		this.users.add(user);
	}

	/**
	 * Gets the search str.
	 *
	 * @return the search str
	 */
	public String getSearchStr() {
		return searchStr;
	}

	/**
	 * Sets the search str.
	 *
	 * @param searchStr
	 *            the new search str
	 */
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public static Request reformulateRequest(Request oldRequest) {
		Request newRequest;
		switch (oldRequest.getRequest()) {
		case ADD:
			newRequest = new Request(Requests.DELETE, null, oldRequest.getPersonalData(), null);
			break;
		case DELETE:
			newRequest = new Request(Requests.ADD, null, oldRequest.getPersonalData(), null);
			break;
		case EDIT:
			newRequest = new Request(Requests.EDIT, null, oldRequest.getPersonalDataNew(),
					oldRequest.getPersonalData());
			break;
		default:
			newRequest = null;
		}
		return newRequest;

	}
}
