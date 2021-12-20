import java.util.ListIterator;

public class GLListIterator<E> implements ListIterator<E> {

	GenericList<E>.Node<E> curr;
	int count = 0;
	public GLListIterator(GenericList<E>.Node<E> head, int index) {
		count = index;
		curr = head;
		for(int i = 0; i < index; i++) {
			curr = curr.next;
		}
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return curr.next != null;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		E val = null;
		val = curr.data;
		curr = curr.next;
		count++;
		return val;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return curr.prev != null;
	}

	@Override
	public E previous() {
		// TODO Auto-generated method stub
		E val = null;
		val = curr.data;
		curr = curr.prev;
		count--;
		return val;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return (count-1);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
	}

}
