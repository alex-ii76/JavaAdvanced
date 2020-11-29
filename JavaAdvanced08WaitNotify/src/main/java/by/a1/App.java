package by.a1;

public class App {
	public static void main(String[] args) {
		Queue queue = new Queue();
		Pile pile = new Pile(100);
		Truck truck = new Truck(6);
		new Loader(truck, pile,queue);
		new Unloader(truck, pile,queue);
		new Driver(truck, pile,queue);
	}
}
