package by.drozdovskaya.xml.run;

import by.drozdovskaya.xml.parser.FamilyParser;
import by.drozdovskaya.xml.parser.dom.FamilyDomParser;
import by.drozdovskaya.xml.parser.sax.FamilySaxParser;
import by.drozdovskaya.xml.parser.stax.FamilyStaxParser;

public class MainApp {

	public static void main(String[] args) {
		
		FamilyParser familyParser = new FamilyDomParser();
		System.out.println(familyParser.parseFamilyDoc("resources/Family.xml"));
		familyParser = new FamilySaxParser();
		System.out.println(familyParser.parseFamilyDoc("resources/Family.xml"));
		familyParser = new FamilyStaxParser();
		System.out.println(familyParser.parseFamilyDoc("resources/Family.xml"));
	

	}

}
