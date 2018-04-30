import java.util.Random;

public class Consumer extends Thread {
	public static final int MAX_CAP = 33;
	
	Random r = new Random();
	int howMuchToTake = r.nextInt(MAX_CAP) + 1;
	

	public void run() {
		System.out.println("Consumer is trying to take : " + howMuchToTake);
		try {
			takeFromBasket();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void takeFromBasket() throws InterruptedException {
		while(true) {
			// Consumer waits for the basket to not be empty and for there to be enough to take
			while (Basket.basket.size() == 0 || howMuchToTake > Basket.basket.size()) 
				wait();
	
			// Consumer takes amount from basket
			Basket.take(howMuchToTake);
			System.out.println("Consumer has taken!");
	
			// Will alert the Producer to produce
			notify();
		}
	}
}
