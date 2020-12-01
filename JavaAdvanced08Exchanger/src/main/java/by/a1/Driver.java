package by.a1;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Driver implements Runnable {
	private Truck truck;
	private Pile pile;
	private Exchanger<Truck> loadExchanger;
	private Exchanger<Truck> unloadExchanger;

	public Driver(Exchanger<Truck> loadExchanger, Exchanger<Truck> unloadExchanger, Truck truck, Pile pile) {
		this.truck = truck;
		this.pile = pile;
		this.loadExchanger = loadExchanger;
		this.unloadExchanger = unloadExchanger;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {

			try {
				truck = loadExchanger.exchange(null);
				truck.goToUnloader();
				TimeUnit.SECONDS.sleep(5);

				unloadExchanger.exchange(truck);
				truck = unloadExchanger.exchange(null);

				truck.goToLoader();
				TimeUnit.SECONDS.sleep(5);
				truck = loadExchanger.exchange(truck);

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
