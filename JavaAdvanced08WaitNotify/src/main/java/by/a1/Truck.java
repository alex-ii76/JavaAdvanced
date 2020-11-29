package by.a1;

import java.util.concurrent.TimeUnit;

public class Truck {
	private volatile int quantity;
	private int maxQuantity;

	public Truck(int maxQuantity) {
		super();
		this.quantity = 0;
		this.maxQuantity = maxQuantity;
	}

	public int put(int quantity) {
		// System.out.println("PUT");

		int ret = 0;
		if (this.quantity + quantity > this.maxQuantity) {
			ret = this.quantity + quantity - this.maxQuantity;
			this.quantity = this.maxQuantity;
		} else {
			this.quantity += quantity;
		}

		return ret;
	}

	public void get(int quantity) {
		// System.out.println("GET");
		int sand = quantity;

		if (quantity >= this.quantity) {
			sand = this.quantity;
			this.quantity = 0;
		} else {
			this.quantity -= quantity;
		}

		System.out.println(
				String.format("The Unloader took %s kg of sand from the truck. Left %s", sand, this.getQuantity()));

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goToUnloader() {
		System.out.println("The Driver is going to the Unloader");
	}

	public void goToLoader() {
		System.out.println("The Driver is going to the Loader");
	}

	public boolean isEmpty() {
		return this.quantity == 0;
	}

	public boolean isFull() {
		return this.quantity == this.maxQuantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

}
