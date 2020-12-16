package by.a1;

public class WeightColorProduct extends WeightProduct{
	String color;
	
	public WeightColorProduct(String name, double price, double weight,String color) {
		super(name, price, weight);
		this.color = color;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "WeightColorProduct [color=" + color 
				+ ", weight=" + weight 
				+ ", name=" + name 
				+ ", price=" + price
				+ "]";
	}

}
