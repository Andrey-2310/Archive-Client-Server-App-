package Server.ParsingXML.parsers;



import java.io.IOException;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Server.ParsingXML.LoadData;
import general.person.Person;


/**
 * The Class JDOMParser.
 */
public class JDOMParser extends LoadData {

	protected Person load(ZipInputStream zin) {
		Person personData = new Person();
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(zin);
			Element classElement = document.getRootElement();
			List<Element> persons = classElement.getChildren();

			Element person = persons.get(1);
			personData.setSurname(person.getChild("surname").getText());
			personData.setName(person.getChild("name").getText());
			personData.setFathername(person.getChild("fathername").getText());
			personData.setPhone(person.getChild("phone").getText());
			personData.setEmail(person.getChild("email").getText());
			personData.setSex(person.getChild("sex").getText());

			Element personJob = persons.get(0);
			personData.setJobExperience(personJob.getChild("experience").getText());
			personData.setJobPosition(personJob.getChild("position").getText());
			personData.setEmployer(personJob.getChild("employer").getText());

			System.out.println(personData.toString());
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return personData;
	}
}

