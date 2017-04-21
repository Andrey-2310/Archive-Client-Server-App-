package Server.Parsers;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class PersonParser extends DefaultHandler {
	
	@Override 
	public void startDocument() throws SAXException {
		System.out.println("Start parse XML..."); 
	}
}
