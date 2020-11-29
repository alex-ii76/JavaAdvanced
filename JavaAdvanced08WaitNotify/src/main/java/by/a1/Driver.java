package by.a1;

import java.util.concurrent.TimeUnit;

public class Driver implements Runnable {
	private Truck truck;
	private Pile pile;
	private Queue queue;

	public Driver(Truck truck, Pile pile, Queue queue) {
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
		if (queue.getStep() != 2 && queue.getStep() != 4) {
			try {
				queue.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}}

			synchronized (truck) {
				synchronized (pile) {
					if (pile.isEmpty() && truck.isEmpty()) {
						break;
					}
				}
				
				if (queue.getStep() == 2 || queue.getStep() == 4) {
					if (queue.getStep() == 2) {
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

					queue.changeStep();
				}
			}
			queue.notify();
			}
		}
	}
}
