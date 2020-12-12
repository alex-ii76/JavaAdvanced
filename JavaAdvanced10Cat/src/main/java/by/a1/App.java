package by.a1;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {

		List<Class<? extends Cat>> cats = new ArrayList<>();
		cats.add(FatCat.class);		
		cats.add(BlackCat.class);
		cats.add(UglyCat.class);
		cats.add(ThinCat.class);

		for (Class<? extends Cat> cat : cats) {
			Annotation[] annotations = cat.getAnnotations();
			boolean isfarsh = true;
			System.out.println("��� : " + cat.getSimpleName());
			for (Annotation anno : annotations) {
				if (anno instanceof Blohable) {
					System.out.println(cat.getSimpleName() + "���������. �� ���� �� ��������");
					isfarsh = false;
					break;
				} else if (anno instanceof Color) {
					String catcolor = ((Color) anno).catcolor();
					System.out.println("����: " + catcolor);
					if (catcolor.equals("black")) {
						double random = Math.random();
						if (random < 0.5) {
							System.out.println(cat.getSimpleName() + " ������. �������. �� ���� �� ��������");
							break;
						} else {
							System.out.println(cat.getSimpleName() + " ������. �� �� �������. �� ���� ��������");
						}
					}
				} else if (anno instanceof CatYears) {
					int catOld = ((CatYears) anno).old();
					System.out.println("������� ����: " + catOld + " ���");
					if (catOld > 2) {
						System.out.println(cat.getSimpleName() + " ������. �� ���� �� ��������");
						isfarsh = false;
						break;
					}
				}
			}
			if (isfarsh)
				System.out.println(cat.getSimpleName() + " �� ����.... ");
		}
	}
}
