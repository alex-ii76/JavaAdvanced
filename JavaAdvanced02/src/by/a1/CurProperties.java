package by.a1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JLabel;

public class CurProperties {
	private static final String CONFIG_FILE_NAME = "resources/config.properties";

	private String pathResources;
	private String language;
	private String country;
	private String message_welcome;
	private String message_text1;
	private String message_text2;
	private String batton_save_text;
	private String batton_load_text;

	public CurProperties() {
		super();
		try {
			this.language = "us";
			this.country = "US";
			getLocalePropValues();
			loadLocale();
			setProperties();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ChangeProperties(String language, String country) {

		this.language = language;
		this.country = country;
		setProperties();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMessage_welcome() {
		return message_welcome;
	}

	public void setMessage_welcome(String message_welcome) {
		this.message_welcome = message_welcome;
	}

	public String getMessage_text1() {
		return message_text1;
	}

	public void setMessage_text1(String message_text1) {
		this.message_text1 = message_text1;
	}

	public String getMessage_text2() {
		return message_text2;
	}

	public void setMessage_text2(String message_text2) {
		this.message_text2 = message_text2;
	}

	public String getBatton_save_text() {
		return batton_save_text;
	}

	public void setBatton_save_text(String batton_save_text) {
		this.batton_save_text = batton_save_text;
	}

	public String getBatton_load_text() {
		return batton_load_text;
	}

	public void setBatton_load_text(String batton_load_text) {
		this.batton_load_text = batton_load_text;
	}

	public String gePathResources() {
		return pathResources;
	}

	private void setProperties() {
		
		setProperties(this.language, this.country);
	}

	public void setProperties(String _language, String _country) {
		Locale locale = new Locale(_language, _country);
		ResourceBundle resourceBundle = null;

		try {
			File file = new File(this.pathResources);
			URL[] urls = { file.toURI().toURL() };
			URLClassLoader loader = new URLClassLoader(urls);
			resourceBundle = ResourceBundle.getBundle("MessageBundle", locale, loader);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		this.message_welcome = resourceBundle.getString("message.welcome");
		this.message_text1 = resourceBundle.getString("message.text1");
		this.message_text2 = resourceBundle.getString("message.text2");
		this.batton_save_text = resourceBundle.getString("batton_save.text");
		this.batton_load_text = resourceBundle.getString("batton_load.text");
	}

	private InputStream getInputStream(String filePath) throws IOException {

		InputStream is = null;

		try {
			System.out.println(filePath);
			is = this.getClass().getClassLoader().getResourceAsStream(filePath);
			if (is == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			try {
				File file = new File(filePath);
				System.out.println(file.getAbsolutePath());

				is = new FileInputStream(file);
				if (is == null) {
					throw new Exception();
				}
				pathResources = Paths.get(file.getParent()).toAbsolutePath().toString();
			} catch (Exception e2) {
				try {
					File file = new File(new File(
							this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
									.getParentFile().getPath()
							+ "/" + filePath);
					System.out.println(file.getAbsolutePath());
					is = new FileInputStream(file);
					pathResources = Paths.get(file.getParent()).toAbsolutePath().toString();
				} catch (Exception e4) {
					// TODO: handle exception
				}
			}
		}

		return is;
	}

	private void getLocalePropValues() throws IOException {

		Properties prop = new Properties();

		try (InputStream inputStream = getInputStream(CONFIG_FILE_NAME);) {

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + CONFIG_FILE_NAME + "' not found in the classpath");
			}

			this.country = prop.getProperty("country");
			this.language = prop.getProperty("language");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

	}

	public void saveLocale() {

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(System.getProperty("user.dir") + "/" + "locale.dat"))) {
			Locale locale = new Locale(this.language, this.country);
			oos.writeObject(locale);
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

	}
	
	public void loadLocale() {
		 try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "/" + "locale.dat")))
	        {
	            Locale l=(Locale)ois.readObject();
	           this.language = l.getLanguage();
	           this.country = l.getCountry();
	        }
	        catch(Exception ex){
	        	System.out.println(ex.getMessage());
	        }

	}

}