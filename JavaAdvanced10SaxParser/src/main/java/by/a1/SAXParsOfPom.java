package by.a1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParsOfPom extends DefaultHandler {

	private String modelVersion, name, url;
	private Project project;
	private String groupId;
	private Plugin plugin;
	private Execution execution;
	private Dependency dependency;

	private String artifactId;
	private String version;
	private String scope;
	private String lastElementName;

	private boolean isproperties = false;
	private boolean isdependency = false;
	private boolean isplugin = false;
	private boolean isExecution = false;

	public SAXParsOfPom(Project project) {
		super();
		this.project = project;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		lastElementName = qName;
		if (lastElementName == "project") {
			int length = attributes.getLength();
			for (int i = 0; i < length; i++) {
				project.addToAttr(attributes.getValue(i), attributes.getValue(i));

			}
		} else if (lastElementName == "properties") {
			isproperties = true;
		} else if (lastElementName == "dependency") {
			dependency = new Dependency();
			isdependency = true;
		} else if (lastElementName == "plugin") {
			isplugin = true;
			plugin = new Plugin();
		} else if (lastElementName == "execution") {
			isExecution = true;
			execution = new Execution();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String information = new String(ch, start, length);
		information = information.replace("\n", "").trim();

		if (!information.isEmpty()) {
			if (isproperties) {
				project.addProperty(lastElementName, information);
			} else if (isplugin) {
				if (lastElementName == "artifactId") {
					plugin.setArtifactId(information);
				} else if (lastElementName == "version") {
					plugin.setVersion(information);
				} else if (lastElementName == "descriptorRef") {
					plugin.getConfiguration().addDescriptorRef(information);
				} else if (lastElementName == "mainClass") {
					plugin.getConfiguration().getArchive().getManifest().setMainClass(information);
				}
				if (isExecution) {

					if (lastElementName == "id") {
						execution.setId(information);
					} else if (lastElementName == "goal") {
						execution.addGoal(information);
					} else if (lastElementName == "phase") {
						execution.setPhase(information);
					}

				}
			} else if (isdependency) {
				switch (lastElementName) {
				case "groupId":
					dependency.setGroupId(information);
					break;
				case "artifactId":
					dependency.setArtifactId(information);
					break;
				case "version":
					dependency.setVersion(information);
					break;
				case "scope":
					dependency.setScope(information);
					break;
				}
			} else {
				switch (lastElementName) {
				case "groupId":
					project.setGroupId(information);
					break;
				case "artifactId":
					project.setArtifactId(information);
					break;
				case "version":
					project.setVersion(information);
					break;
				case "modelVersion":
					project.setModelVersion(information);
					break;
				case "name":
					project.setName(information);
					break;
				case "url":
					project.setUrl(information);
					break;
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName == "dependency") {
			project.addDependency(dependency);
			isdependency = false;
		} else if (qName == "properties") {
			isproperties = false;
		} else if (qName == "plugin") {
			isplugin = false;
			project.getBuild().addPlagin(plugin);
		} else if (qName == "execution") {
			plugin.addExecutions(execution);
			isExecution = false;
		}
	}
}
