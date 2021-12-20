import java.util.Iterator;

public class GLLIterator<E> implements Iterator<E> {

	GenericList<E>.Node<E> curr;
	public GLLIterator(GenericList<E>.Node<E> head) {
		curr = head;
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
		curr = curr.next;
		return val;
	}

}
