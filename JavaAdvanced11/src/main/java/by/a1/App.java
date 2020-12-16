package by.a1;


public class App 
{
    public static void main( String[] args )
    {
    	 Shelf<Product> shelf = new Shelf<>();
         shelf.putProduct(new Product("window1", 302.0D));
         shelf.putProduct(new Product("table1", 211.45D));
         shelf.putProduct(new Product("chair1", 233.77D));
        
        
         shelf.putProduct(new WeightProduct("window2", 302.0D, 20.0D));
         shelf.putProduct(new WeightProduct("table2", 211.45D, 50.0D));
         shelf.putProduct(new WeightProduct("chair2", 233.77D, 10.0D));
    
         shelf.putProduct(new WeightColorProduct("window3", 302.0D, 20.0D, "while"));
         shelf.putProduct(new WeightColorProduct("table3", 211.45D, 50.0D, "gray"));
         shelf.putProduct(new WeightColorProduct("chair3", 233.77D, 10.0D, "black"));
         
         shelf.show();

    }
}
