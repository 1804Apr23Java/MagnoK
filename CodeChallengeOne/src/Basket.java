import java.util.LinkedList;

public class Basket {
	// Shared basket among Producer and Consumer
	public static final LinkedList<String> basket = new LinkedList<>();	
	
	public synchronized static void add(int howMuchToAdd) {
		while(basket.size() < howMuchToAdd) {
			basket.add("Ready to Consume!");
		}
	}
	
	public synchronized static void take(int howMuchToTake) {
		// Consumer takes amount from basket
		for (int i = 0; i < howMuchToTake; i++) {
			// Removes from head
			basket.removeFirst();
		}
	}
}
