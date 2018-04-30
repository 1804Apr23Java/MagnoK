
public class Driver {

	public static void main(String[] args) throws InterruptedException {
			
		// Puts items into basket when it is empty
		//Producer p = new Producer();
		Thread threadP = new Producer();
		
		// Attempts to remove a randomized # of items from basket
		//Consumer c = new Consumer();
		Thread threadC = new Consumer();
		
		threadP.start();
		threadC.start();
		
		threadP.join();
		threadC.join();
	}
}
