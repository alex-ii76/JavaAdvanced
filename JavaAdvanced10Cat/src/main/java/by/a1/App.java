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
			System.out.println("Кот : " + cat.getSimpleName());
			for (Annotation anno : annotations) {
				if (anno instanceof Blohable) {
					System.out.println(cat.getSimpleName() + "блохастый. На фарш не подходит");
					isfarsh = false;
					break;
				} else if (anno instanceof Color) {
					String catcolor = ((Color) anno).catcolor();
					System.out.println("Цвет: " + catcolor);
					if (catcolor.equals("black")) {
						double random = Math.random();
						if (random < 0.5) {
							System.out.println(cat.getSimpleName() + " черный. Везучий. На фарш не подходит");
							break;
						} else {
							System.out.println(cat.getSimpleName() + " черный. Но не везучий. На фарш подходит");
						}
					}
				} else if (anno instanceof CatYears) {
					int catOld = ((CatYears) anno).old();
					System.out.println("Возраст кота: " + catOld + " лет");
					if (catOld > 2) {
						System.out.println(cat.getSimpleName() + " старый. На фарш не подходит");
						isfarsh = false;
						break;
					}
				}
			}
			if (isfarsh)
				System.out.println(cat.getSimpleName() + " на фарш.... ");
		}
	}
}
