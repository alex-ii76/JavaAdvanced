package by.a1;

import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private Truck truck;
	private Pile pile;
	private Queue queue;

	public Loader(Truck truck, Pile pile, Queue queue) {
		super();
		this.truck = truck;
		this.pile = pile;
		this.queue = queue;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				if (queue.getStep() != 1) {
				try {
					queue.wait();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}}
				synchronized (truck) {
					if (queue.getStep() == 1) {

						while (!pile.isEmpty() && !truck.isFull()) {
							int sand = pile.getQuantity(3);
							System.out.println(String.format("The Loader took %s kg of sand from the heap. Left %s kg",
									sand, pile.total()));
							int q = truck.put(sand);
							// ≈сли не влезло в тачку, возвращаем в кучу
							pile.put(q);
							System.out.println(String.format("The Loader put %s kg of sand in the truck. Ttuck: %s kg",
									sand, truck.getQuantity()));
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
									e.printStackTrace();
							}
							if (pile.isEmpty() || truck.isFull()) {
								queue.changeStep();
							}
						}
					}
				}
				queue.notify();
				synchronized (pile) {
					if (pile.isEmpty()) {
						break;
					}
				}
			}
		}
	}
}
