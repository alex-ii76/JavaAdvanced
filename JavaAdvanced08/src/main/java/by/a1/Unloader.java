package by.a1;

import java.util.concurrent.Semaphore;

public class Unloader implements Runnable {
	private Truck truck;
	private Pile pile;
	private Semaphore semaphore;

	public Unloader(Truck truck, Pile pile, Semaphore semaphore) {
		super();
		this.truck = truck;
		this.pile = pile;
		this.semaphore = semaphore;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			// System.out.printf("Разгрузчик ждет свою очередь");
			try {
				semaphore.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// System.out.println("The loader doing something");
			synchronized (truck) {
				synchronized (pile) {
					if (pile.isEmpty() && truck.isEmpty()) {
						break;
					}
				}
				if (truck.getStep() == 3) {
					while (!truck.isEmpty()) {
						truck.get(2);						
					}
					truck.changeStep();
				}
				semaphore.release();
			}
		}
	}
}
