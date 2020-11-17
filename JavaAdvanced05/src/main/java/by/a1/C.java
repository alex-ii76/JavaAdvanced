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

public class C extends JFrame {
	private static final String FILE_IN_JAR = "txt/menu.txt";
	private static final String IMG_IN_JAR = "img/menu.png";
	private static final String FILE_NEAR_JAR = "resources/txt/menu2.txt";
	private static final String IMG_NEAR_JAR = "resources/img/menu2.png";
	public C()  {
		super("HomeWork04");
		
	// File in jar
		add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource(IMG_IN_JAR))));
		try(InputStream is = this.getClass().getClassLoader().getResourceAsStream(FILE_IN_JAR);) {
			addLabel("ОСЕННЕЕ МЕНЮ",is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// File near jar
		File f;
		File fileTXT = new File(FILE_NEAR_JAR);
		File fileIMG = new File(IMG_NEAR_JAR);
		if(!fileTXT.exists())
		try {
			f = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
			
			fileIMG = new File(f.getPath()+"/"+IMG_NEAR_JAR);
			fileTXT = new File(f.getPath()+"/"+FILE_NEAR_JAR);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!fileIMG.exists()) {
			add(new JLabel("Отсудствует файл: "+fileIMG.getPath()));
		}else
		 {
			add(new JLabel(new ImageIcon(fileIMG.getPath())));			
		} 
			
		if(!fileTXT.exists()) {
			add(new JLabel("Отсудствует файл: "+fileTXT.getPath()));
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
