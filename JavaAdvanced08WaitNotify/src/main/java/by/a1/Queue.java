package by.a1;

public class Queue {
	private volatile int step;// 1- Loader, 2 - Driver is going to the Unloader, 3 - Unloade, 4 - Driver is
// going to the Loader

	public Queue() {
		super();
		this.step = 1;
	}
	
	public int getStep() {		
		return step;
	}

	public void changeStep() {
		if (this.step == 4) {
			this.step = 1;
		} else {
			this.step++;
		}		
		//System.out.println("ÿ¿√: "+this.step);
	}

}
