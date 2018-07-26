package by.drozdovskaya.xml.parser.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import by.drozdovskaya.xml.entity.Child;
import by.drozdovskaya.xml.entity.Families;
import by.drozdovskaya.xml.entity.Family;
import by.drozdovskaya.xml.entity.Father;
import by.drozdovskaya.xml.entity.Mother;
import by.drozdovskaya.xml.parser.FamilyParser;

public class FamilyStaxParser implements FamilyParser {

	@Override
	public Families parseFamilyDoc(String path) {
		Families families = new Families();
		Family family = null;
		Mother mother = null;
		Father father = null;
		Child child = null;
		int count_mother = 0;
		int count_father = 0;
		int count_child = 0;
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader;
		try {

			xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
			family = new Family();
			while (xmlEventReader.hasNext()) {

				XMLEvent xmlEvent = xmlEventReader.nextEvent();
				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();
					if (startElement.getName().getLocalPart().equals("Family")) {

						Attribute idAttr = startElement.getAttributeByName(new QName("id"));
						if (idAttr != null) {
							Integer.parseInt(idAttr.getValue());
						}
					}

					if (startElement.getName().getLocalPart().equals("mother")) {
						xmlEvent = xmlEventReader.nextEvent();
						mother = new Mother();
						Attribute maidenNameAttr = startElement.getAttributeByName(new QName("maiden-name"));
						mother.setMaidenName(maidenNameAttr.getValue());
						count_mother++;
					} else if (count_mother > count_father) {
						mother = addMother(xmlEvent, startElement, xmlEventReader, mother);

					}
					if (startElement.getName().getLocalPart().equals("father")) {
						xmlEvent = xmlEventReader.nextEvent();
						father = new Father();
						count_father++;
					} else if (count_mother == count_father && count_child == 0) {
						father = addFather(xmlEvent, startElement, xmlEventReader, father);
					}
					if (startElement.getName().getLocalPart().equals("child")) {
						xmlEvent = xmlEventReader.nextEvent();
						child = new Child();
						count_child++;
					} else if (count_child > 0) {
						child = addChild(xmlEvent, startElement, xmlEventReader, child);
						if (child.getGender() != null) {
							count_child = 0;
						}

					}
				}

				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					switch (endElement.getName().getLocalPart()) {
					case "mother":
						family.setMother(mother);
						break;
					case "father":
						family.setFather(father);
						break;
					case "child":
						family.addChild(child);
						break;
					case "family":
						families.addFamily(family);
						family = new Family();
						break;
					}
				}
			}
		} catch (FileNotFoundException | XMLStreamException e) {

			e.printStackTrace();
		}

		return families;
	}

	private Mother addMother(XMLEvent xmlEvent, StartElement startElement, XMLEventReader xmlEventReader,
			Mother mother) {
		try {
			switch (startElement.getName().getLocalPart()) {
			case "name":
				xmlEvent = xmlEventReader.nextEvent();
				mother.setName(xmlEvent.asCharacters().getData());
				break;
			case "surname":
				xmlEvent = xmlEventReader.nextEvent();
				mother.setSurname(xmlEvent.asCharacters().getData());
				break;
			case "age":
				xmlEvent = xmlEventReader.nextEvent();
				mother.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
				break;
			}
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
		return mother;
	}

	private Father addFather(XMLEvent xmlEvent, StartElement startElement, XMLEventReader xmlEventReader,
			Father father) {
		try {
			switch (startElement.getName().getLocalPart()) {
			case "name":
				xmlEvent = xmlEventReader.nextEvent();
				father.setName(xmlEvent.asCharacters().getData());
				break;
			case "surname":
				xmlEvent = xmlEventReader.nextEvent();
				father.setSurname(xmlEvent.asCharacters().getData());
				break;
			case "age":
				xmlEvent = xmlEventReader.nextEvent();
				father.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
				break;
			}
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
		return father;
	}

	private Child addChild(XMLEvent xmlEvent, StartElement startElement, XMLEventReader xmlEventReader, Child child) {
		try {
			switch (startElement.getName().getLocalPart()) {
			case "name":
				xmlEvent = xmlEventReader.nextEvent();
				child.setName(xmlEvent.asCharacters().getData());
				break;
			case "surname":
				xmlEvent = xmlEventReader.nextEvent();
				child.setSurname(xmlEvent.asCharacters().getData());
				break;
			case "age":
				xmlEvent = xmlEventReader.nextEvent();
				child.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
				break;
			case "gender":
				xmlEvent = xmlEventReader.nextEvent();
				child.setGender(xmlEvent.asCharacters().getData());
				break;
			}
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
		return child;
	}

}
