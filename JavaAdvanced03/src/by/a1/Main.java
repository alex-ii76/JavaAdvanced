package by.a1;

import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {

	public Main() {
		// TODO Auto-generated method stub
		// java -cp bin by.a1.Main
		Console console = System.console();

		if (console == null) {
			System.out.println("No Console");
			System.exit(-1);
		}
		// считываем данные с консоли
		String comand = null;
		String executionPath = System.getProperty("user.dir");
		// System.out.println(executionPath);
		Path path = Paths.get(executionPath);
		File file = new File(executionPath);

		while (true) {
			System.out.print(path.toString() + ">");
			comand = console.readLine();
			comand = comand.replace("  ", " ").toUpperCase();
			// System.out.println("comand: "+comand);

			String[] arr = comand.split(" ");

			switch (arr[0]) {
			case "HELP":

				System.out.println("DIR	Вывод списка файлов и подпапок из указанной папки.");
				System.out.println("CD смена текущей папки");
				System.out.println("EXIT	Выход.");
				System.out.println("MD Создает каталог.");
				System.out.println("COPY	Копирует файл/папку");
				System.out.println("OPEN	Читает файл");

				System.out.print(path.toString() + "> ");
				break;
			case "DIR":
				for (File f : file.listFiles()) {
					if (!file.isFile())
						System.out.println(f.getName());
				}

				for (File f : file.listFiles()) {
					if (file.isFile())
						System.out.println(f.getName());
				}
				// System.out.print(path.toString()+"> ");
				break;

			case "CD\\":
				path = path.getRoot();
				file = new File(path.toString());
				// System.out.print(path.toString()+"> ");
				break;
			case "CD..":
				path = path.getParent();
				file = new File(path.toString());
				// System.out.print(path.toString()+"> ");
				break;
			case "EXIT":
				System.exit(0);
				break;
			case "CD":
				if (arr.length == 2) {
					File f = new File(arr[1]);
					if (f.exists() && f.isDirectory()) {
						path = Paths.get(f.getAbsolutePath());
					} else {

						f = new File(path.toString() + "/" + arr[1]);
						if (f.exists() && f.isDirectory()) {
							path = Paths.get(f.getAbsolutePath());
						}
					}
				}
				file = new File(path.toString());
				break;
			case "MD":
				if (arr.length == 2) {
					File f = new File(arr[1]);
					if (f.getAbsolutePath() != arr[1]) {
						f = new File(path.toString() + "/" + arr[1]);
					}

					if (f.mkdirs()) {
						System.out.println(arr[1] + " is created successful");
					} else {
						System.out.println(arr[1] + " not created");
					}
				}
				break;
			case "COPY":
				if (arr.length == 3) {
					File f1 = new File(arr[1]);
					if (f1.getAbsolutePath() != arr[1]) {
						f1 = new File(path.toString() + "/" + arr[1]);
					}

					File f2 = new File(arr[2]);
					if (f2.getAbsolutePath() != arr[2]) {
						f2 = new File(path.toString() + "/" + arr[2]);
					}

					if (!f1.exists()) {
						System.out.println(arr[1] + " not exists");

					} else {
						Path p1 = Paths.get(f1.getAbsolutePath());
						Path p2 = Paths.get(f2.getAbsolutePath());
						try {
							Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error: " + e.getLocalizedMessage());
						}
					}
				}
				break;
			case "OPEN":
				if (arr.length == 2) {
					File f = new File(arr[1]);
					if (f.getAbsolutePath() != arr[1]) {
						f = new File(path.toString() + "/" + arr[1]);
					}

					if (!f.exists()) {
						System.out.println(arr[1] + " not exists");

					} else {

						FileInputStream fis = null;
						InputStreamReader isr = null;
						try {
							fis = new FileInputStream(f);
							isr = new InputStreamReader(fis, "UTF-8");
							BufferedReader br = new BufferedReader(isr);
							String text;
							while ((text = br.readLine()) != null) {
								System.out.println(text);
							}

						} catch (FileNotFoundException e) {
							e.printStackTrace();

						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						finally {

							try {
								fis.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				}
				break;
			default:

				break;
			}

		}

	}

	public static void main(String[] args) {

		new Main();
	}

}
