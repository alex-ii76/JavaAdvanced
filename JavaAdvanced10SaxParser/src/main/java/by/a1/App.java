package by.a1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class App {

	private static ArrayList<Dependency> dependencees = new ArrayList<>();

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		Project project = new Project();
		File f = new File("resources/pom.xml");
		System.out.println(f.getAbsolutePath());

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		SAXParsOfPom handler = new SAXParsOfPom(project);
		parser.parse(f, handler);
		System.out.println(project.toString());
	}
}