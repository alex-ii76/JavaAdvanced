package by.a1;

import java.util.ArrayList;
import java.util.List;

public class Build {
	private List<Plugin> plagins;

	public Build() {
		plagins = new ArrayList<Plugin>();
	}
	
	public List<Plugin> getPlagins() {
		return plagins;
	}

	public void addPlagin(Plugin plagin) {
		plagins.add(plagin);
	}

	@Override
	public String toString() {
		return "Build [plagins=" + plagins.toString() + "]";
	}
	
}
