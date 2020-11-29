package by.a1;

import java.util.concurrent.Semaphore;


public class App {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);	
		Pile pile = new Pile(100);
		Truck truck = new Truck(6);
		new Loader(truck, pile,semaphore);
		new Unloader(truck, pile,semaphore);
		new Driver(truck, pile,semaphore);
	}
}
