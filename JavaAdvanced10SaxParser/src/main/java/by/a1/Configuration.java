package by.a1;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
	private List<String> descriptorRefs;
	private Archive archive;
	
	
	public Configuration() {
		archive = new Archive();
		descriptorRefs = new ArrayList<String>();
	}
	
	@Override
	public String toString() {
		return "Configuration [descriptorRefs=" + descriptorRefs + ", archive=" + archive.toString() + "]";
	}

	public Archive getArchive() {
		return archive;
	}

	public List<String> getDescriptorRefs() {
		return descriptorRefs;
	}

	
	public void addDescriptorRef(String descriptorRef) {		
		descriptorRefs.add(descriptorRef);
	}
	
	
}
