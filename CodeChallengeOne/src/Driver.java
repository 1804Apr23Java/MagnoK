import java.util.LinkedList;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		
		// Shared basket among Producer and Consumer
		LinkedList<String> basket = new LinkedList<>();	
			
		// Puts items into basket when it is empty
		//Producer p = new Producer();
		Thread threadP = new Producer(basket);
		
		// Attempts to remove a randomized # of items from basket
		//Consumer c = new Consumer();
		Thread threadC = new Consumer(basket);
		
		synchronized(basket) {
			threadP.start();
			threadC.start();
			
			threadP.join();
			threadC.join();
		}
	}
}
