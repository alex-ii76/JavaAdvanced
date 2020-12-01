package by.a1;

import java.util.concurrent.Exchanger;

public class App {
    public static void main(String[] args ) {
    	
    	Pile pile = new Pile(100);
		Truck truck = new Truck(6);
		Exchanger<Truck> loaderDriver = new Exchanger<>();
    	Exchanger<Truck> unloaderDriver = new Exchanger<>();
    	
    	new Loader(loaderDriver, truck,pile);
    	new Driver(loaderDriver, unloaderDriver, null, pile);
    	new Unloader(unloaderDriver, null, pile);
    }
}
