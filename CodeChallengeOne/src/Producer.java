
public class Producer extends Thread{
	public static final int MAX_CAP = 33;
	
	public void run() {
		// Continuous loop
		while (true) {
			try {
				addToBasket();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Basket.basket.getFirst());
		}
	}

	public synchronized void addToBasket() throws InterruptedException {
		
		// Producer waits for basket to be empty or waits if it is full
		while(Basket.basket.size() != 0 || Basket.basket.size() == MAX_CAP)
			wait();
		
		// Producer starts adding to the basket
		Basket.basket.add("Ready to Consume!");
		System.out.println("Producer produced!");
		
		// Will alert the Consumer to consume
		notify();
	}
	
}
