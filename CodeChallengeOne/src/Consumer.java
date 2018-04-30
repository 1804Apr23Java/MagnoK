import java.util.LinkedList;
import java.util.Random;

public class Consumer extends Thread {
	public static final int MAX_CAP = 33;
	public LinkedList<String> basket;

	public Consumer(LinkedList<String> basket) {
		this.basket = basket;
	}

	Random r = new Random();
	boolean take = true;

	public void run() {
		try {
			takeFromBasket();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void takeFromBasket() throws InterruptedException {
		while(true) {
			int howMuchToTake = r.nextInt(MAX_CAP) + 1;
			
			// Will not take from empty basket
			if((basket.size() > 0)) {
				if(howMuchToTake < basket.size()) {
					System.out.println("Consumer is trying to take : " + howMuchToTake);
					// Consumer takes amount from basket
					Basket.take(basket, howMuchToTake);
					System.out.println("Consumer has taken!");
				
					// Will alert the Producer to produce
					notify();
				}
			}
		} 
	}
}
