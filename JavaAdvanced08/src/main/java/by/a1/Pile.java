package by.a1;

public class Pile {
	private volatile int quantity;

	public Pile(int quantity) {
		super();
		this.quantity = quantity;
	}

	public int getQuantity(int quantity) {
		if (quantity>this.quantity) {
			int res = this.quantity;
			this.quantity = 0;
			return res;
		}		
		this.quantity-=quantity;
		return quantity;
	}
	
	public boolean isEmpty() {
		return this.quantity == 0;
	}
	
	public void put(int quantity) {
		 this.quantity+=quantity;
	}
	
	public int  total() {
		return this.quantity;
	}


}
