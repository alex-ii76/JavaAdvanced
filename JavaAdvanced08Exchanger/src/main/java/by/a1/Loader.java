package by.a1;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private Truck truck;
	private Pile pile;
	private Exchanger<Truck> exchanger;

	public Loader(Exchanger<Truck> exchanger, Truck truck, Pile pile) {
		this.exchanger = exchanger;
		this.truck = truck;
		this.pile = pile;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("loader start");
				while (!pile.isEmpty() && !truck.isFull()) {
					int sand = pile.getQuantity(3);
					System.out.println(String.format("The Loader took %s kg of sand from the heap. Left %s kg", sand,
							pile.total()));
					int q = truck.put(sand);
					// ≈сли не влезло в тачку, возвращаем в кучу
					pile.put(q);
					System.out.println(String.format("The Loader put %s kg of sand in the truck. Ttuck: %s kg", sand,
							truck.getQuantity()));
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("loader end");

				truck = exchanger.exchange(truck);
				truck = exchanger.exchange(null);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (pile) {
				if (pile.isEmpty()) {
					break;
				}
			}

		}

	}

}
