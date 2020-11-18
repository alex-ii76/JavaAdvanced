package by.a1;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Mine mine = new Mine(1000);
		System.out.println("Main thread started...");

		Barrack barrack = new Barrack(mine);

		for (int i = 0; i < 5; i++) {
			barrack.createMiner();
		}
		while (true) {
			synchronized (mine) {
				if (mine.isEmpty())
					break;
			}
		}

		System.out.println("Main thread finished...");

	}
}
