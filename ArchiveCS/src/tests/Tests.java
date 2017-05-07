package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Server.ParsingXML.LoadData;
import Server.ParsingXML.parsers.DOMParser;
import Server.ParsingXML.parsers.JDOMParser;
import Server.ParsingXML.parsers.SAXParser;
import Server.ParsingXML.parsers.StAXParser;
import client.ClientConnector;
import general.Request;
import general.Request.Requests;
import general.person.Person;
import general.serialization.SerializeManager;



public class Tests {

	@Test
	public void serializationTest() {
		SerializeManager<String> smStr = new SerializeManager<String>();
		String tmp = smStr.serialize("Привет");
		String result = new String();
		assertEquals("Привет", smStr.deserialize(tmp, result));

		SerializeManager<Integer> smInt = new SerializeManager<Integer>();
		String tmpInt = smInt.serialize(12345);
		Integer resultInt = null;
		assertEquals(new Integer("12345"), smInt.deserialize(tmpInt, resultInt));

	}

	@Test
	public void SAXParserTest() {
		String fileName = new String("AndreyRedkovskiy");

		SerializeManager<Person> sm = new SerializeManager<Person>();
		Person person1 = new Person();
		person1 = sm.load(person1, fileName);

		Person person2 = new Person();
		LoadData ld = new SAXParser();
		person2 = ld.loadPersonalData(fileName);
System.out.println(person1.toString());
System.out.println(person2.toString());
		assertEquals(person1.toString(), person2.toString());
	}

	@Test
	public void StAXParserTest() {
		String fileName = new String("AndreyRedkovskiy");

		SerializeManager<Person> sm = new SerializeManager<Person>();
		Person person1 = new Person();
		person1 = sm.load(person1, fileName);

		Person person2 = new Person();
		LoadData ld = new StAXParser();
		person2 = ld.loadPersonalData(fileName);

		assertEquals(person1.toString(), person2.toString());
	}

	@Test
	public void DOMParserTest() {
		String fileName = new String("AndreyRedkovskiy");

		SerializeManager<Person> sm = new SerializeManager<Person>();
		Person person1 = new Person();
		person1 = sm.load(person1, fileName);

		Person person2 = new Person();
		LoadData ld = new DOMParser();
		person2 = ld.loadPersonalData(fileName);

		assertEquals(person1.toString(), person2.toString());
	}

	@Test
	public void JDOMParserTest() {
		String fileName = new String("AndreyRedkovskiy");

		SerializeManager<Person> sm = new SerializeManager<Person>();
		Person person1 = new Person();
		person1 = sm.load(person1, fileName);

		Person person2 = new Person();
		LoadData ld = new JDOMParser();
		person2 = ld.loadPersonalData(fileName);

		assertEquals(person1.toString(), person2.toString());
	}

	@Test
	public void connectionTest() {
		ClientConnector cc = new ClientConnector("admin", "admin");
		assertEquals(cc.setConnection(), "admin");
		assertNull(cc.setNewRequest(new Request(Requests.EXIT, null, null, null)));
	}

}
