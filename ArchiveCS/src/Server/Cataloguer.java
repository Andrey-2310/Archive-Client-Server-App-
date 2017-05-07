package Server;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import Server.ParsingXML.parsers.DOMParser;
import Server.ParsingXML.parsers.JDOMParser;
import Server.ParsingXML.parsers.SAXParser;
import Server.ParsingXML.parsers.StAXParser;
import Server.ParsingXML.LoadData;
import Server.ParsingXML.UsedParses;
import Server.ParsingXML.Validator;
import general.person.Person;
import general.serialization.SerializeManager;
import general.Response;
import general.Request.Requests;

/**
 * The Class Cataloger. Used as a DAO layer between xml storage of an archive
 * and the program.
 */
public class Cataloguer {

	/** The used parser. */
	private static LoadData usedParser = useSAXParser();

	/**
	 * This Hashtable used to search by name and surname quickly. Key =
	 * name+surname, Value = address of the xml doc.
	 */
	public static Hashtable<String, String> htNameSurname = new Hashtable<String, String>();

	/**
	 * This Hashtable used to search by Email quickly. Key = email, Value =
	 * address of the xml doc.
	 */
	public static Hashtable<String, String> htEmail = new Hashtable<String, String>();

	/**
	 * This Hashtable used to search by phone quickly. Key = phone, Value =
	 * address of the xml doc.
	 */
	public static Hashtable<String, String> htPhone = new Hashtable<String, String>();

	/**
	 * Adds a new person to the cataloger
	 *
	 * @param person
	 *            the person
	 * @return the response
	 */
	public static Response add(Person person) {
		if (validate(person)) {
			htNameSurname.put(new String("" + person.getName() + person.getSurname()), person.getXmlFileName());
			htEmail.put(person.getEmail(), person.getXmlFileName());
			htPhone.put(person.getPhone(), person.getXmlFileName());
			new SerializeManager<Person>().save(person, person.getXmlFileName());
			ServerStart.loggerServer.info(
					new String("Client " + person.getSurname() + " " + person.getName() + " was succesfully added"));
			saveData();
			return new Response(Requests.ADD, null, null, true);
		}
		ServerStart.loggerServer.info(
				new String("Client " + person.getSurname() + " " + person.getName() + " wasn't added"));
		return new Response(Requests.ADD, null, null, false);
	}

	/**
	 * Validates the document before savin
	 *
	 * @param person
	 *            the person
	 * @return true, if successful
	 */
	private static boolean validate(Person person) {
		String s = new String();
		s = new SerializeManager<Person>().serialize(person);

		if (!Validator.validate(s)) {
			ServerStart.loggerServer.error(
					new String("Validation of a document " + person.getName() + person.getSurname() + " failed"));
			return false;
		}

		ServerStart.loggerServer
				.info(new String("Document " + person.getName() + person.getSurname() + " validated successfully"));
		return true;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public static Response getAll() {
		Enumeration<String> files = htEmail.elements();
		Vector<Person> cataloger = new Vector<Person>();
		while (files.hasMoreElements()) {
			cataloger.addElement(parse(files.nextElement()));
		}
		ServerStart.loggerServer.info(new String("Request to get all data is served"));
		return new Response(Requests.SHOW_ALL, cataloger, null, true);
	}

	/**
	 * Parses the.
	 *
	 * @param nextElement
	 *            the next element
	 * @return the personal data
	 */
	private static Person parse(String nextElement) {
		Person person = new Person();
		person = usedParser.loadPersonalData(nextElement);
		return person;
	}

	@UsedParses("SAXParser")
	public static LoadData useSAXParser() {
		ServerStart.loggerServer.info(new String("Parser changed to SAX"));
		usedParser = new SAXParser();
		return usedParser;
	}

	@UsedParses("StAXParser")
	public static LoadData useStAXParser() {
		ServerStart.loggerServer.info(new String("Parser changed to StAX"));
		usedParser = new StAXParser();
		return usedParser;
	}

	@UsedParses("DOMParser")
	public static LoadData useDOMParser() {
		ServerStart.loggerServer.info(new String("Parser changed to DOM"));
		usedParser = new DOMParser();
		return usedParser;
	}

	@UsedParses("JDOMParser")
	public static LoadData useJDOMParser() {
		ServerStart.loggerServer.info(new String("Parser changed to JDOM"));
		usedParser = new JDOMParser();
		return usedParser;
	}

	/**
	 * Find by phone.
	 *
	 * @param phone
	 *            the phone
	 * @return the personal data
	 */
	public static Person findByPhone(String phone) {
		Person person = new Person();
		String fileName = null;
		fileName = htPhone.get(phone);
		if (fileName == null) {
			return null;
		}
		if (new SerializeManager<Person>().load(person, fileName) != null) {
			ServerStart.loggerServer.info(new String("Request to find by phone is served"));
			return person;
		}
		ServerStart.loggerServer.error(new String("Error in reading file"));
		return null;
	}

	/**
	 * Find by name and surname.
	 *
	 * @param searchStr
	 *            the search str
	 * @return the personal data
	 */
	public static Person findByNameAndSurname(String searchStr) {
		Person person = new Person();
		String fileName = null;
		fileName = htNameSurname.get(searchStr);
		if (fileName == null) {
			return null;
		}
		if (new SerializeManager<Person>().load(person, fileName) != null) {
			ServerStart.loggerServer.info(new String("Request to find by name and surname is served"));
			return person;
		}
		ServerStart.loggerServer.info(new String("Error in reading file"));
		return null;
	}

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the personal data
	 */
	public static Person findByEmail(String email) {
		Person person = new Person();
		String fileName = null;
		fileName = htEmail.get(email);
		if (fileName == null) {
			return null;
		}
		if (new SerializeManager<Person>().load(person, htEmail.get(email)) != null) {
			return person;
		}
		ServerStart.loggerServer.info(new String("Error in reading file"));
		return null;
	}

	/**
	 * Edits the.
	 *
	 * @param personalDataOld
	 *            the personal data old
	 * @param personalDataNew
	 *            the personal data new
	 * @return the response
	 */
	public static Response edit(Person personalDataOld, Person personalDataNew) {
		delete(personalDataOld);
		add(personalDataNew);
		return new Response(Requests.EDIT, null, null, true);
	}

	/**
	 * Delete.
	 *
	 * @param personalData
	 *            the personal data
	 * @return the response
	 */
	public static Response delete(Person personalData) {

		htEmail.remove(personalData.getEmail());
		htNameSurname.remove(new String("" + personalData.getName() + personalData.getSurname()));
		htPhone.remove(personalData.getPhone());

		ServerStart.loggerServer.info(new String(
				"Client " + personalData.getSurname() + " " + personalData.getName() + " was succesfully deleted"));
		saveData();
		return new Response(Requests.DELETE, null, null, true);
	}

	/**
	 * Save data.
	 */
	private static void saveData() {
		new SerializeManager<Hashtable<String, String>>().save(htEmail, "Email");
		new SerializeManager<Hashtable<String, String>>().save(htNameSurname, "NameSurname");
		new SerializeManager<Hashtable<String, String>>().save(htPhone, "Phone");

	}

	/**
	 * Load data.
	 */
	public static void loadData() {
		htEmail = new SerializeManager<Hashtable<String, String>>().load(htEmail, "Email");
		htNameSurname = new SerializeManager<Hashtable<String, String>>().load(htNameSurname, "NameSurname");
		htPhone = new SerializeManager<Hashtable<String, String>>().load(htPhone, "Phone");
	}

	/**
	 * Find.
	 *
	 * @param searchStr
	 *            the search str
	 * @return the response
	 */
	public static Response find(String searchStr) {
		Vector<Person> persons = new Vector<Person>();

		Person email = findByEmail(searchStr);
		if (email != null)
			persons.add(email);

		Person nameAndSurname = findByNameAndSurname(searchStr);
		if (nameAndSurname != null)
			persons.add(nameAndSurname);

		Person phone = findByPhone(searchStr);
		if (phone != null)
			persons.add(phone);

		return new Response(Requests.FIND, persons, null, true);
	}

	public static Response changeParser(String newParserDescription) {
		switch (newParserDescription) {
		case "SAX":
			usedParser = useSAXParser();
			break;
		case "StAX":
			usedParser = useStAXParser();
			break;
		case "DOM":
			usedParser = useDOMParser();
			break;
		case "JDOM":
			usedParser = useJDOMParser();
			break;
		default:
			return new Response(Requests.CHANGE_PARSER, null, null, false);
		}
		return new Response(Requests.CHANGE_PARSER, null, null, true);
	}
	
	public static void setUpPersons() {
		add(new Person("Redkoskiy", "Andrey", "Dmitrievich", "+375291974714", "redkovskiyandrey@gmail.com",
				"EPAM", "2", "Developer", "Man"));
		add(new Person("Trusevich", "Valentin", "Sergeevich", "+375298573010", "vtrusevich@gmail.com", "OOO",
				"2", "Security", "Man"));
		add(new Person("Karachun", "Anton", "Antonovich", "+3752911122330", "tonymontana@tut.by", "BSUIR",
				"2", "Student", "Man"));

	}
}
