package by.a1;

import java.util.ArrayList;
import java.util.List;

public class Shelf<T extends Product> {
	List<T> listOfProducts;
	
	public Shelf() {
		listOfProducts = new ArrayList<>();
	}

	public void putProduct(T product) {
		listOfProducts.add(product);
	}

	
	@Override
	public String toString() {		
		StringBuilder builder = new StringBuilder();		
		for (T product : listOfProducts) {
			builder.append(product.showProduct() + "\n");
		}		
		return builder.toString();
	}


	public  void show() {
		System.out.println("Shelf contains \n"+this.toString());
	}

}
