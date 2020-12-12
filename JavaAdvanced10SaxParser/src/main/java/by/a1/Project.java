package by.a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Project {
	private String modelVersion, groupId, artifactId, version, name, url;
	private Map<String, String> attributes;
	private Map<String, String> properties;
	private Build build;

//	@Override
//	public String toString() {
//		return "Project [modelVersion=" + modelVersion + ", groupId=" + groupId + ", artifactId=" + artifactId
//				+ ", version=" + version + ", name=" + name + ", url=" + url + ", attributes=" + attributes
//				+ ", properties=" + properties + ", dependencees=" + dependencees.toString() + "]";
//	}
	
	

	private ArrayList<Dependency> dependencees;

	@Override
	public String toString() {
		return "Project [modelVersion=" + modelVersion + ", groupId=" + groupId + ", artifactId=" + artifactId
				+ ", version=" + version + ", name=" + name + ", url=" + url + ", attributes=" + attributes.toString()
				+ ", properties=" + properties.toString() + ", build=" + build.toString() + ", dependencees=" + dependencees.toString() + "]";
	}

	public Project() {
		attributes = new HashMap<String, String>();
		properties = new HashMap<String, String>();
		dependencees = new ArrayList<Dependency>();
		build = new Build();
	}

	public Build getBuild() {
		return build;
	}

	
	public void addDependency(Dependency dependency) {
		dependencees.add(dependency);
	}

	public void addProperty(String key, String value) {
		properties.put(key, value);
	}

	public ArrayList<Dependency> getDependencees() {
		return dependencees;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public void addToAttr(String key, String value) {
		attributes.put(key, value);
	}

	public Map<String, String> getMap() {
		return attributes;
	}

	public void setMap(Map<String, String> map) {
		this.attributes = map;
	}

	public String getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
