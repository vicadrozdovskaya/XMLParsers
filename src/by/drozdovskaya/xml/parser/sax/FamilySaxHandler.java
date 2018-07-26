package by.drozdovskaya.xml.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.drozdovskaya.xml.entity.Child;
import by.drozdovskaya.xml.entity.Families;
import by.drozdovskaya.xml.entity.Family;
import by.drozdovskaya.xml.entity.Father;
import by.drozdovskaya.xml.entity.Mother;

public class FamilySaxHandler extends DefaultHandler {

	private Families families;
	private Family family;
	private Mother mother;
	private Father father;
	private Child child;
	String thisElement = "";
	String parentTag = "";
	int id = 0;

	public Families getFamilies() {
		return families;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("startDocument()");
		families = new Families();
	}

	@Override
	public void endDocument() throws SAXException {

		System.out.println("endDocument()");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		thisElement = qName;
		System.out.println("startElement");
		switch (localName) {
		case "family":
			family = new Family();
			id = Integer.parseInt(attributes.getValue("id"));
			break;
		case "mother":
			mother = new Mother();
			mother.setMaidenName(new String(attributes.getValue("maiden-name")));
			parentTag = localName;
			break;
		case "father":
			father = new Father();
			parentTag = localName;
			break;
		case "child":
			child = new Child();
			parentTag = localName;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		thisElement = "";
		switch (localName) {
		case "family":
			Family f = new Family();
			f.setMother(family.getMother());
			f.setFather(family.getFather());
			f.setChildren(family.getChildren());
			families.addFamily(f);
			family = new Family();
			System.out.println("add family");
		}
		System.out.println("endElement");
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (id != 0) {
			switch (parentTag) {
			case "mother":
				addMother(ch, start, length);
				break;
			case "father":
				addFather(ch, start, length);
				break;
			case "child":
				addChild(ch, start, length);
				break;
			}
		}
	}

	private void addMother(char[] ch, int start, int length) {
		if (thisElement.equals("name")) {
			mother.setName(new String(ch, start, length));

		}
		if (thisElement.equals("surname")) {
			mother.setSurname(new String(ch, start, length));

		}
		if (thisElement.equals("age")) {
			mother.setAge(new Integer(new String(ch, start, length)));
			System.out.println(mother);
			family.setMother(mother);
		}
	}

	private void addFather(char[] ch, int start, int length) {
		if (thisElement.equals("name")) {
			father.setName(new String(ch, start, length));

		}
		if (thisElement.equals("surname")) {
			father.setSurname(new String(ch, start, length));

		}
		if (thisElement.equals("age")) {
			father.setAge(new Integer(new String(ch, start, length)));
			System.out.println(father);
			family.setFather(father);
		}
	}

	private void addChild(char[] ch, int start, int length) {
		if (thisElement.equals("name")) {
			child.setName(new String(ch, start, length));

		}
		if (thisElement.equals("surname")) {
			child.setSurname(new String(ch, start, length));

		}
		if (thisElement.equals("age")) {
			child.setAge(new Integer(new String(ch, start, length)));
		}
		if (thisElement.equals("gender")) {
			child.setGender(new String(ch, start, length));
			System.out.println(child);
			family.addChild(child);
		}
	}

}
