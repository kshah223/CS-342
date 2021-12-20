import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class GenericList<T> implements Iterable<T> {

	private Node<T> head;
	private int length = 0;
	
	public GenericList(T val) {
		Node<T> newNode = new Node<T>(val);
		head = newNode;
		length++;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public void setHead(Node<T> head) {
		this.head = head;
	}
	
	public Node<T> getHead() {
		return this.head;
	}

	public void print() {
		Node<T> tmp = head;
		if(tmp == null) {
			System.out.println("Empty list!!");
		}
		while(tmp != null) {
			System.out.println(tmp.data);
			tmp = tmp.next;
		}
	}
	
	public abstract void add(T data);
	
	public T delete() {
		if(head == null) {
			return null;
		}
		T data = head.data;
		head.prev = null;
		head = head.next;
		length--;
		return data;
	}

	public ArrayList<T> dumpList() {
		ArrayList<T> arr1 = new ArrayList<T>();
		
		Node<T> temp = head;
		for(int i = 0; i < length; i++) {
			arr1.add(temp.data);
			temp = temp.next;
		}
		return arr1;
	}
	
	public T get(int index) {
		if(index < 0 || index > length-1) {
			return null;
		}
		
		Node<T> temp = head;
		for(int i = 0; i < index-1; i++) {
			temp = temp.next;
		}
		
		T val = temp.data;
		return val;
	}
	
	public T set(int index, T element) {
		if(index < 0 || index > length-1) {
			return null;
		}
		
		Node<T> temp = head;
		for(int i = 0; i < index-1; i++) {
			temp = temp.next;
		}
		
		T val = temp.data;
	    temp.data = element;
	    return val;
	}
	
	class Node<T>{
	    T data;
	    Node<T> next;
	    Node<T> prev;

	    public Node(T data) {
	       this.data = data;
		   this.next = null;
		   this.prev = null;
	    }
	}
	
	public ListIterator<T> listIterator(int index) {
		if(index >= length) {
			return null;
		}
		return new GLListIterator(head,index);
	}
	
	public Iterator<T> descendingIterator() {
		return new ReverseGLLIterator(head);
	}
	
	public Iterator<T> iterator() {
		return new GLLIterator(head);
	}
}
