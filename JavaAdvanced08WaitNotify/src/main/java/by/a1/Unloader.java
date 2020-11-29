package by.a1;

public class Unloader implements Runnable {
	private Truck truck;
	private Pile pile;
	private Queue queue;

	public Unloader(Truck truck, Pile pile, Queue queue) {
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
				if (queue.getStep() != 3) {
					try {
						queue.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				synchronized (truck) {
					synchronized (pile) {
						if (pile.isEmpty() && truck.isEmpty()) {
							break;
						}
					}

					if (queue.getStep() == 3) {
						while (!truck.isEmpty()) {
							truck.get(2);
						}
						queue.changeStep();
					}
				}
				queue.notify();
			}

		}
	}
}
