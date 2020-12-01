package by.a1;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Gamer implements Callable<Integer> {
	private String name;
	private int timeOut;

	public Gamer(String name) {
		super();
		this.name = name;
		this.timeOut = 5 + (int)(Math.random() * 16);
		
	}

	public Integer call() throws Exception {
		System.out.println(String.format("Gamer %s is strarting %s seconds", getName(),gettimeOut()));
		
		TimeUnit.SECONDS.sleep(gettimeOut());
		
		System.out.println(String.format("Gamer %s has started", getName()));
		
		return gettimeOut();
	}

	public String getName() {
		return name;
	}

	public int gettimeOut() {
		return timeOut;
	}



}
