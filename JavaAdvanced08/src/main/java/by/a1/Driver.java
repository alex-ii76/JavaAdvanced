package by.a1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Driver implements Runnable {
	private Truck truck;
	private Pile pile;
	private Semaphore semaphore;

	public Driver(Truck truck, Pile pile, Semaphore semaphore) {
		super();
		this.truck = truck;
		this.pile = pile;
		this.semaphore = semaphore;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			// System.out.printf("Транспортер ждет свою очередь");
			try {
				semaphore.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			// System.out.println("The loader doing something");
			synchronized (truck) {
				synchronized (pile) {
					if (pile.isEmpty() && truck.isEmpty()) {
						break;
					}
				}
				if (truck.getStep() == 2 || truck.getStep() == 4) {
					if (truck.getStep() == 2) {
						truck.goToUnloader();
					} else {
						truck.goToLoader();
					}
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					truck.changeStep();
				}
				semaphore.release();
			}
		}
	}
}
