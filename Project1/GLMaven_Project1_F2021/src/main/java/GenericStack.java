import java.util.Iterator;

public class GenericStack<T> extends GenericList<T> {
	Node<T> tail;

	public GenericStack(T val) {
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
			newNode.next = getHead();
			setHead(newNode);
			setLength(getLength() + 1);
			Node<T> tmp = getHead();
			tmp.prev = newNode;
		}
	}
	
	public void push(T data) {
		add(data);
	}
	
	public T pop() {
		return delete();
	}
	

}
