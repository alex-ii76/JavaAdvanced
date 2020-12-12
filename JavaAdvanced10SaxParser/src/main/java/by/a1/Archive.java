package by.a1;

public class Archive {
	private Manifest manifest;

	public Archive() {

		this.manifest = new Manifest();
	}
	
	public Manifest getManifest() {
		return manifest;
	}

	@Override
	public String toString() {
		return "Archive [manifest=" + manifest.toString() + "]";
	}

}
