package by.a1;

import java.util.ArrayList;
import java.util.List;

public class Plugin {
	private String artifactId;
	private String version;
	private Configuration configuration;
	private List<Execution> executions;
	
	public Plugin() {
		configuration = new Configuration();
		executions = new ArrayList<Execution>();
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	
	public List<Execution> getExecutions() {
		return executions;
	}
	
	public void addExecutions(Execution execution) {
		executions.add(execution);
	}

	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	@Override
	public String toString() {
		return "Plugin [artifactId=" + artifactId + ", version=" + version + ", configuration=" + configuration.toString()
				+ ", executions=" + executions.toString() + "]";
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
