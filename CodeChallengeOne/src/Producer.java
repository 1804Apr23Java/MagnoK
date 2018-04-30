
public class Producer extends Thread{
	public static final int MAX_CAP = 33;
	
	public void run() {
		// Continuous loop
		try {
			addToBasket();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void addToBasket() throws InterruptedException {
		// Producer waits for basket to be empty or waits if it is full
		while(Basket.basket.size() != 0 || Basket.basket.size() == MAX_CAP)
			wait();
			
		// Producer fills the basket
		while(Basket.basket.size() < MAX_CAP) {
			Basket.basket.add("Ready to Consume!");
			System.out.println("Producer produced!");
			System.out.println(Basket.basket.getFirst());
		}
			
		// Will alert the Consumer to consume
		notify();
	}
	
}
