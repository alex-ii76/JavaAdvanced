package by.a1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.io.InputStream;
import java.net.URISyntaxException;

public class PropConnection {
	
	private static final String FILE_PROPERTIES = "resources/connection.properties";
	
	private String host;
	private String user;
	private String password;
	
	public PropConnection() {
		super();	
		try {
			getPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getHost() {
		return host;
	}

	public String getUser() {
		return user;
	}


	public String getPassword() {
		return password;
	}	
	
	public String getPropValues() throws IOException {
		 
		String result = null;
		File f;
		File file = new File(FILE_PROPERTIES);
		if(!file.exists())
			try {
				f = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
				file = new File(f.getPath()+"/"+FILE_PROPERTIES);
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		try( InputStream inputStream = new FileInputStream(file);) {
			Properties prop = new Properties();
			//String propFileName = "config.properties";
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + FILE_PROPERTIES + "' not found in the classpath");
			}
 
			this.host = prop.getProperty("host");
			this.user = prop.getProperty("user");
			this.password = prop.getProperty("password");
			
			result = "Connection properties. host = " + this.host + ", user = " + this.user + ", password = " + this.password;
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return result;
	}
}
