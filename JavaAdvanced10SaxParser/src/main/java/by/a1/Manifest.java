package by.a1;

public class Manifest{
	
	private String mainClass;

	public Manifest() {
		
	}

	@Override
	public String toString() {
		return "Manifest [mainClass=" + mainClass + "]";
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	
}
