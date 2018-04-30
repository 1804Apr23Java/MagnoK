import java.util.Random;

public class Producer extends Thread{
	public static final int MAX_CAP = 33;
	
	Random r = new Random();
	int howMuchToAdd = r.nextInt(MAX_CAP) + 1;
	
	public void run() {
		System.out.println("Producer added : " + howMuchToAdd);
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
		Basket.add(howMuchToAdd);
		System.out.println("Producer produced!");
			
		// Will alert the Consumer to consume
		notify();
		
		// Producer Thread Sleeps
		Thread.sleep(100);
	}
	
}
