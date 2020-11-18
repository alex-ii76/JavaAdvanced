package by.a1;

import java.util.concurrent.TimeUnit;

public class Miner  implements Runnable {
	private String name;
	private int quantity;
	private Mine mine;

	public Miner(Mine mine, String name) {
		super();
		this.mine = mine;
		this.name = name;
		this.quantity = 0;
		new Thread(this).start();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity += quantity;
	}

	public void printStaus() {
		System.out.println(
				String.format("%s, gold: %s. %s gold left in the mine "
				, this.name
				, this.quantity
				, mine.getTotalGold()
				));

	}

	@Override
	public void run() {
		while (true) {			
			try {	
				TimeUnit.SECONDS.sleep(1);
				synchronized (mine) {
					if (mine.isEmpty()) {
						break;
					}
					setQuantity(mine.getGold(3));
					printStaus();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
