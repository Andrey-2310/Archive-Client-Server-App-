package Server.ParsingXML.parsers;

import java.util.zip.ZipInputStream;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Server.ServerStart;
import Server.ParsingXML.LoadData;
import general.person.Person;

/**
 * The Class SAXParser.
 */
public class SAXParser extends LoadData {

	protected Person load(ZipInputStream zin) {
		Handler handler = null;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
			handler = new Handler();
			saxParser.parse(zin, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler.getPerson();
	}
}

class Handler extends DefaultHandler {

	boolean bSurname = false;
	boolean bName = false;
	boolean bFathername = false;
	boolean bPhone = false;
	boolean bEmail = false;
	boolean bSex = false;
	boolean bEmployer = false;
	boolean bExperience = false;
	boolean bPosition = false;
	Person person = new Person();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("personInfo")) {
			ServerStart.loggerServer.info("Start Element : personInfo");
		} else if (qName.equalsIgnoreCase("personJob")) {
			ServerStart.loggerServer.info("Start Element : personJob");
		} else if (qName.equalsIgnoreCase("surname")) {
			bSurname = true;
		} else if (qName.equalsIgnoreCase("name")) {
			bName = true;
		} else if (qName.equalsIgnoreCase("fathername")) {
			bFathername = true;
		} else if (qName.equalsIgnoreCase("phone")) {
			bPhone = true;
		} else if (qName.equalsIgnoreCase("email")) {
			bEmail = true;
		} else if (qName.equalsIgnoreCase("sex")) {
			bSex = true;
		} else if (qName.equalsIgnoreCase("employer")) {
			bEmployer = true;
		} else if (qName.equalsIgnoreCase("Experience")) {
			bExperience = true;
		} else if (qName.equalsIgnoreCase("Position")) {
			bPosition = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("personInfo")) {
			ServerStart.loggerServer.info("End Element : personInfo");
		}
		if (qName.equalsIgnoreCase("personJob")) {
			ServerStart.loggerServer.info("End Element : personJob");
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bSurname) {
			person.setSurname(new String(ch, start, length));
			bSurname = false;
		}
		if (bName) {
			person.setName(new String(ch, start, length));
			bName = false;
		}
		if (bFathername) {
			person.setFathername(new String(ch, start, length));
			bFathername = false;
		}
		if (bPhone) {
			person.setPhone(new String(ch, start, length));
			bPhone = false;
		}
		if (bEmail) {
			person.setEmail(new String(ch, start, length));
			bEmail = false;
		}
		if (bSex) {
			person.setSex(new String(ch, start, length));
			bSex = false;
		}
		if (bEmployer) {
			person.setEmployer(new String(ch, start, length));
			bEmployer = false;
		}
		if (bExperience) {
			person.setJobExperience(new String(ch, start, length));
			bExperience = false;
		}
		if (bPosition) {
			person.setJobPosition(new String(ch, start, length));
			bPosition = false;
		}
	}

	public Person getPerson() {
		return person;
	}
}
