import java.awt.List;
import java.util.LinkedList;
import java.util.Random;

public class Producer extends Thread {
	public static final int MAX_CAP = 33;
	public LinkedList<String> basket;

	public Producer(LinkedList<String> basket) {
		this.basket = basket;
	}

	Random r = new Random();

	public void run() {
		try {
			addToBasket();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void addToBasket() throws InterruptedException {
		while (true) {
			int howMuchToAdd = r.nextInt(MAX_CAP) + 1;
			// Producer waits for basket to be empty or waits if it is full
			while (basket.size() != 0 || basket.size() == MAX_CAP) 
				wait();

			System.out.println("Producer is adding : " + howMuchToAdd);
			// Producer fills the basket
			Basket.add(basket, howMuchToAdd);
			System.out.println("Producer is done producing!");

			// Will alert the Consumer to consume
			notify();

			// Producer Thread Sleeps
			sleep(1000);
		}
	}
}
