package by.a1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private Truck truck;
	private Pile pile;
	private Semaphore semaphore;

	public Loader(Truck truck, Pile pile, Semaphore semaphore) {
		super();
		this.truck = truck;
		this.pile = pile;
		this.semaphore = semaphore;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
		//	System.out.printf("Грузчик ждет свою очередь");
			try {
				semaphore.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// System.out.println("The loader doing something");
			synchronized (truck) {

				if (truck.getStep() == 1) {

					while (!pile.isEmpty() && !truck.isFull()) {
						int sand = pile.getQuantity(3);
						System.out.println(String.format("The Loader took %s kg of sand from the heap. Left %s kg", sand,
								pile.total()));
						int q = truck.put(sand);
						// Если не влезло в тачку, возвращаем в кучу
						pile.put(q);
						System.out.println(String.format("The Loader put %s kg of sand in the truck. Ttuck: %s kg",
								sand, truck.getQuantity()));
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (pile.isEmpty() || truck.isFull()) {
							truck.changeStep();
						}
					}
				}
				semaphore.release();
				synchronized (pile) {
					if (pile.isEmpty()) {
						break;
					}
				}

			}

		}
	}

}
