public class GenericQueue<T> extends GenericList<T> {
	Node<T> tail;

	public GenericQueue(T val) {
		super(val);
		Node<T> newNode = new Node<T>(val);
		setHead(newNode);
		tail = newNode;
		setLength(1);
	}

	public T removeTail() {
		if(tail == null) {
			return null;
		}
		else {
			if(getHead() == tail) {
				setHead(null);
				tail = null;
				return null;
			}
			else {
				Node<T> curr = getHead();
				while(curr.next != tail) {
					curr = curr.next;
				}
				tail = curr;
				tail.next = null;
				setLength(getLength()-1);
				return tail.data;
			}
		}
	}
	
	public void add(T data) {
		Node<T> newNode = new Node(data);
		
		if(getHead() == null) {
			setHead(newNode);
			setLength(getLength() + 1);
			tail = newNode;
		}
		else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			setLength(getLength() + 1);
		}
	}
	
	public void enqueue(T data) {
		add(data);
	}
	
	public T dequeue() {
		return delete();
	}
	
}
