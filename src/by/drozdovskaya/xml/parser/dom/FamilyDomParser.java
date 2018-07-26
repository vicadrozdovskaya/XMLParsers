package by.drozdovskaya.xml.parser.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.drozdovskaya.xml.entity.Child;
import by.drozdovskaya.xml.entity.Families;
import by.drozdovskaya.xml.entity.Family;
import by.drozdovskaya.xml.entity.Father;
import by.drozdovskaya.xml.entity.Mother;
import by.drozdovskaya.xml.parser.FamilyParser;

public class FamilyDomParser implements FamilyParser {

	public Families parseFamilyDoc(String path) {
		Families families = new Families();

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(path);
			Element element = document.getDocumentElement();
			System.out.println("Document element " + element.getTagName());
			NodeList childNodes = element.getChildNodes();

			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if (node.getNodeName().equals("family")) {
					Family family = new Family();
					Mother mother = getMotherFromXML(node);
					family.setMother(mother);

					Father father = getFatherFromXML(node);
					family.setFather(father);

					NodeList nodelist = node.getChildNodes();
					for (int j = 0; j < nodelist.getLength(); j++) {
						NodeList nodeChild = nodelist.item(j).getChildNodes();
						for (int k = 0; k < nodeChild.getLength(); k++) {
							Node node3 = nodeChild.item(k);
							if (node3.getNodeName().equals("child")) {
								Child child = getChildFromXML(node3);
								family.addChild(child);
							}
						}
					}

					families.addFamily(family);
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {

			e.printStackTrace();
		}

		return families;
	}

	public NodeList getChild(Node childNodes, String tagName) {
		NodeList nodelist = childNodes.getChildNodes();
		return nodelist;

	}

	private Mother getMotherFromXML(Node child) {
		NodeList nodelist = getChild(child, "mother");
		Mother mother = new Mother();
		for (int j = 0; j < nodelist.getLength(); j++) {
			if (nodelist.item(j).getAttributes() != null) {
				for (int a = 0; a < nodelist.item(j).getAttributes().getLength(); a++) {
					if (nodelist.item(j).getAttributes().item(a).getNodeName().equals("maiden-name")) {
						mother.setMaidenName(nodelist.item(j).getAttributes().item(a).getTextContent());
					}
				}
			}
			if (nodelist.item(j).getNodeName().equals("mother")) {
				NodeList secondNodelist = nodelist.item(j).getChildNodes();

				for (int k = 0; k < secondNodelist.getLength(); k++) {

					Node node2 = secondNodelist.item(k);
					String nodeName = node2.getNodeName();

					if (nodeName.equals("name")) {
						mother.setName(node2.getTextContent());
					} else if (nodeName.equals("surname")) {
						mother.setSurname(node2.getTextContent());
					} else if (nodeName.equals("age")) {
						mother.setAge(Integer.parseInt(node2.getTextContent()));
					}

				}
			}
		}
		return mother;

	}

	private Father getFatherFromXML(Node child) {

		NodeList nodelist = getChild(child, "Father");
		Father father = new Father();
		for (int j = 0; j < nodelist.getLength(); j++) {

			NodeList secondNodelist = nodelist.item(j).getChildNodes();
			for (int k = 0; k < secondNodelist.getLength(); k++) {

				Node node2 = secondNodelist.item(k);
				String nodeName = node2.getNodeName();

				if (nodeName.equals("name")) {
					father.setName(node2.getTextContent());
				} else if (nodeName.equals("surname")) {
					father.setSurname(node2.getTextContent());
				} else if (nodeName.equals("age")) {
					father.setAge(Integer.parseInt(node2.getTextContent()));
				}

			}
		}
		return father;

	}

	private Child getChildFromXML(Node node) {

		NodeList nodelist = getChild(node, "child");
		Child child = new Child();
		for (int j = 0; j < nodelist.getLength(); j++) {

			Node node2 = nodelist.item(j);
			String nodeName = node2.getNodeName();

			if (nodeName.equals("name")) {
				child.setName(node2.getTextContent());
			} else if (nodeName.equals("surname")) {
				child.setSurname(node2.getTextContent());
			} else if (nodeName.equals("age")) {
				child.setAge(Integer.parseInt(node2.getTextContent()));
			} else if (nodeName.equals("gender")) {
				child.setGender(node2.getTextContent());
			}

		}

		return child;
	}

}
