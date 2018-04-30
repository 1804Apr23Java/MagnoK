import java.util.LinkedList;

public class Basket {
	
	static int amountInBasket = 1;
	
	public synchronized static void add(LinkedList<String> basket, int howMuchToAdd) {
		while(basket.size() < howMuchToAdd) {
			basket.add("Ready to Consume!");
			System.out.println("Produced: Amount in basket : " + amountInBasket);
			amountInBasket++;
		}
	}
	
	public synchronized static void take(LinkedList<String> basket,int howMuchToTake) {

		// Consumer takes amount from basket
		for (int i = 0; i < howMuchToTake; i++) {
			// Removes from head
			basket.removeFirst();
			System.out.println("Consumed: Amount in basket : " + amountInBasket);
			amountInBasket--;
		}
	}
}