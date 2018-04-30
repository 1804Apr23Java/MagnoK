import java.util.Random;

public class Consumer extends Thread {

	public void run() {
		// Continuous loop
		while (true) {
			try {
				takeFromBasket();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void takeFromBasket() throws InterruptedException {
		Random r = new Random();
		int howMuchToTake;
		howMuchToTake = r.nextInt(33) + 1;

		// Consumer waits for the basket to not be empty and for there to be enough to take
		while (Basket.basket.size() == 0 || howMuchToTake > Basket.basket.size())
			wait();

		// Consumer takes amount from basket
		for (int i = 0; i < howMuchToTake; i++) {
			// Removes from head
			System.out.println("Consumer consumed! : " + Basket.basket.get(i));
			Basket.basket.removeFirst();
		}

		// Will alert the Producer to produce
		notify();
	}
}
