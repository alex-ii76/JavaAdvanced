package by.a1;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class C {

	private static final String FILE_NEAR_JAR = "resources/config.txt";

	public C()  {

		
	
		
		// File near jar
		File f;
		File fileTXT = new File(FILE_NEAR_JAR);
		if(!fileTXT.exists())
		try {
			f = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
			fileTXT = new File(f.getPath()+"/"+FILE_NEAR_JAR);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		if(!fileTXT.exists()) {
			System.out.println("Отсудствует файл: "+fileTXT.getPath());
	
		}else
		try(InputStream is = new FileInputStream(fileTXT);) {
			addLabel("ПИЦЦЫ",is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setLayout(new GridLayout(2,1));			
		setMinimumSize(new Dimension(800,500));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void addLabel(String title, InputStream is ) //throws Exception
	{
		JLabel label = new JLabel();
			
		try(				
				InputStreamReader isr = new InputStreamReader(is,"UTF-8");
				BufferedReader br =new BufferedReader(isr);
				){
		
		String line;
		String text ="";
		// Menu1
			try {
				while ((line = br.readLine())!=null) {
					text+=line+"<br>";		
				}
				text = "<html>"
						+"---------------------"+title+"---------------------"
						+"<br>"+"<br>"
						+text
						+"</html>";
				label.setText(text);	
				add(label);
			} catch (IOException e) {
				// TODO: handle exception
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		
		new C();
	}

}
