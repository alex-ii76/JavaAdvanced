package by.a1;

public class Mine {
	private volatile int gold;

	public Mine(int gold) {
		super();
		this.gold = gold;
	}

	public boolean isEmpty() {
		return this.gold == 0;
	}
	
	public int getTotalGold() {
		return this.gold;
	}
	
	public int getGold(int quantity) {
		int res = quantity;		
		if (this.gold >= quantity  ) {
			this.gold -= quantity;
		}else {
			res = this.gold;
			this.gold = 0;
		}
		return res;
	}

}
