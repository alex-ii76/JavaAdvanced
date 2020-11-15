package by.a1;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame extends JFrame {

	private static final String PATH_FLAG_BY = "flag_BY.png";
	private static final String PATH_FLAG_UK = "flag_UK.png";
	private static final String PATH_FLAG_US = "flag_US.png";
	private static final String PATH_FLAG_DE = "flag_DE.png";

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JButton buttonSave;
	private JButton buttonLoad;
	private ImageIcon iconUK;
	private ImageIcon iconDE;
	private ImageIcon iconUS;
	private JButton buttonUK;
	private JButton buttonDE;
	private JButton buttonUS;
	private JButton button;
	private CurProperties curProperties;

	public MyFrame(CurProperties _curProperties) {
		super("HomeWork_MyFrame");
		this.curProperties = _curProperties;
		
		// FLAGS
		add(createImageButton( PATH_FLAG_UK));
		add(createImageButton( PATH_FLAG_US));
		add(createImageButton( PATH_FLAG_DE));

		label1 = new JLabel(curProperties.getMessage_welcome());
		label1.setAlignmentX(10);
		

		label2 = new JLabel(curProperties.getMessage_text1());
		
		label3 = new JLabel(curProperties.getMessage_text2());


		buttonSave = createButtonSave(curProperties.getBatton_save_text());

		buttonLoad = createButtonLoad(curProperties.getBatton_load_text());


		add(label1);
		add(label2);
		add(label3);
		add(buttonSave);
		add(buttonLoad);

		setLayout(new GridLayout(2, 1));
		setMinimumSize(new Dimension(800, 500));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private JButton createButtonSave(String text) {
		JButton button = new JButton(text);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curProperties.saveLocale();
			}
		});
		return button;
	}

	private JButton createButtonLoad(String text) {
		JButton button = new JButton(text);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curProperties.loadLocale();
				changeLocale();
			}
		});

		return button;
	}

	private JButton createImageButton(String fileName) {

		String pathFile = curProperties.gePathResources() + "/" +fileName;
		System.out.println("pathFile: " + pathFile);
	//	URL url = this.getClass().getClassLoader().getResource(pathFile);
		ImageIcon icon = new ImageIcon(pathFile);

		JButton button = new JButton(icon);
		button.setSize(32, 32);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fileName: " + fileName);
				switch (fileName) {
		           case  PATH_FLAG_UK:
		        	   changeLocale("uk","UA");
		               break;
		           case  PATH_FLAG_US:
		        	   changeLocale("us","US");
		        	   break;
		           case  PATH_FLAG_DE:
		        	   changeLocale("de","DE");
		               break;		           
		       }
			}
		});

		return button;
	}

	private void changeLocale() {
		changeLocale(curProperties.getLanguage(), curProperties.getCountry());
	}
	private void changeLocale(String language, String country) {

		curProperties.ChangeProperties(language, country);
		label1.setText(curProperties.getMessage_welcome());
		label2.setText(curProperties.getMessage_text1());
		label3.setText(curProperties.getMessage_text2());
		buttonSave.setText(curProperties.getBatton_save_text());
		buttonLoad.setText(curProperties.getBatton_load_text());
	}

}
