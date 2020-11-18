package by.a1;

import java.util.concurrent.TimeUnit;

public class Barrack implements Runnable {
	private Mine mine;
	private int numberMiner;

	public Barrack(Mine mine) {
		super();
		this.mine = mine;
		this.numberMiner = 0;
		new Thread(this).start();
	}

	public Miner createMiner() {
		this.numberMiner++;
		Miner miner = new Miner(mine, "Miner" + Integer.toString(numberMiner));
		return miner;
	}

	@Override
	public void run() {
		System.out.println("Barrack thread started...");
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(10);
				synchronized (mine) {					
					if (mine.isEmpty())
						break;
					createMiner();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Barrack thread finished...");
	}

}
