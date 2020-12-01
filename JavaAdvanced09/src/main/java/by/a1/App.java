package by.a1;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App {
	private static final int COUNT_GAMERS = 10;

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(COUNT_GAMERS);
		System.out.println("Dota2 is starting....");
		// List listFuture = new List();
		ArrayList<Future> listFuture = new ArrayList<Future>();
		for (int i = 0; i < COUNT_GAMERS; i++) {
			Gamer gamer = new Gamer(Integer.toString(i));
			Future<Integer> future = es.submit(gamer);
			listFuture.add(future);
		}

		Boolean f = true;
		for (Future<Integer> future : listFuture) {
			int timeout = 0;
			try {
				timeout = (int) future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (timeout > 15) {
				f = false;					
			}
		}
		
		es.shutdown();
		if (f) {
			System.out.println("Dota2 has started successfully");
		} else
			System.out.println("Connection lost. This game is safe to leave");
	}
}
