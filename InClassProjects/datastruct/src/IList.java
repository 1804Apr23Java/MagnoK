
public class IList<T> {

	private INode<T> head;

	public IList() {
		head = null;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public void add(T t) {
		INode<T> node = new INode<T>(t, null);
		if (this.isEmpty()) {
			head = node;
		} else {
			INode<T> cur = head;
			while (cur.getNext() != null) {
				cur = cur.getNext();
			}
			cur.setNext(node);
		}
	}

	public T remove(int ind) {

		INode<T> prev;
		INode<T> cur;

		if (this.length() == 0) {
			throw new ListOutOfBoundsException();
		}

		// a head exists
		if (ind == 0) {
			cur = head;
			head = head.getNext();
			return cur.getValue();
		}

		prev = head;
		cur = head.getNext();

		for (int i = 1; i < ind; i++) {
			if (cur.getNext() == null)
				throw new ListOutOfBoundsException();
			prev = cur;
			cur = cur.getNext();
		}

		prev.setNext(cur.getNext());
		return cur.getValue();
	}

	public T get(int ind) {
		INode<T> cur = head;
		if (head == null) {
			throw new ListOutOfBoundsException();
		}
		
		for (int i = 0; i < ind; i++) {
			if (cur.getNext() == null)
				throw new ListOutOfBoundsException();
			cur = cur.getNext();
		}
		return cur.getValue();
	}

	public void insert(T t, int ind) {

		INode<T> newNode = new INode<T>(t, null);

		INode<T> prev;
		INode<T> cur;
		
		if (ind > this.length()) {
			throw new ListOutOfBoundsException();
		}

		if (ind == 0) {
			newNode.setNext(head);
			head = newNode;
		} else if (this.length() == 1) {
			head.setNext(newNode);
		} else {
			prev = head;
			cur = head.getNext();

			for (int i = 1; i < ind; i++) {
				prev = cur;
				cur = cur.getNext();
			}
			
			prev.setNext(newNode);
			newNode.setNext(cur);
		}

	}

	public int length() {
		INode<T> cur = head;
		int length = 0;
		while (cur != null) {
			length += 1;
			cur = cur.getNext();
		}
		return length;
	}

}

class INode<T> {

	private T value;
	private INode<T> next;

	public INode(T value, INode<T> next) {
		super();
		this.value = value;
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public INode<T> getNext() {
		return next;
	}

	public void setNext(INode<T> next) {
		this.next = next;
	}
}