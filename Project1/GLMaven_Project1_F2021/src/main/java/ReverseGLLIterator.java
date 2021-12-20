import java.util.Iterator;

public class ReverseGLLIterator<E> implements Iterator<E> {

	GenericList<E>.Node<E> curr;
	public ReverseGLLIterator(GenericList<E>.Node<E> head) {
		curr = head;
		while(curr.next != null) {
			curr = curr.next;
		}
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return curr != null;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		E val = null;
		val = curr.data;
		curr = curr.prev;
		return val;
	}
}
