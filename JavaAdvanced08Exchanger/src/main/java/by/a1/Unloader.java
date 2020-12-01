package by.a1;

import java.util.concurrent.Exchanger;

import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {
	private Truck truck;
	private Pile pile;
	private Exchanger<Truck> exchanger;

	public Unloader(Exchanger<Truck> exchanger, Truck truck, Pile pile) {
		this.truck = truck;
		this.pile = pile;
		this.exchanger = exchanger;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				truck = exchanger.exchange(null);

				System.out.println("unloader start");
				while (!truck.isEmpty()) {
					truck.get(2);
				}
				System.out.println("unloader end");
				exchanger.exchange(truck);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (pile) {
				if (pile.isEmpty() && truck.isEmpty()) {
					break;
				}
			}
		}
	}
}
