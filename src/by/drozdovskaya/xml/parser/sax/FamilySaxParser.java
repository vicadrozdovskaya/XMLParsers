package by.drozdovskaya.xml.parser.sax;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.drozdovskaya.xml.entity.Families;
import by.drozdovskaya.xml.parser.FamilyParser;

public class FamilySaxParser implements FamilyParser {
	
	public Families parseFamilyDoc(String path) {
		Families families = new Families();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			FamilySaxHandler familySaxHandler = new FamilySaxHandler();
			reader.setContentHandler(familySaxHandler);
			reader.parse(path);
			families = familySaxHandler.getFamilies();
			System.out.println(families);
		} catch (SAXException | IOException e) {
			
			e.printStackTrace();
		} 
		return families;
	}

}
