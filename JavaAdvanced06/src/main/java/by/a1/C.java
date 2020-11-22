package by.a1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

public class C {

	private static final String FILE_CONNECTION = "resources/connection.properties";

	public C()  {
		
		// File near jar
		File f;
		File fileTXT = new File(FILE_CONNECTION);
		if(!fileTXT.exists())
		try {
			f = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
			fileTXT = new File(f.getPath()+"/"+FILE_CONNECTION);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		if(!fileTXT.exists()) {
			System.out.println("Отсудствует файл: "+fileTXT.getPath());
	
		}else
		try(InputStream is = new FileInputStream(fileTXT);) {
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	public static void main(String[] args) {
		
		new C();
	}

}
