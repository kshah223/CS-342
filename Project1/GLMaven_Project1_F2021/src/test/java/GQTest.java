import java.util.Iterator;
import org.junit.jupiter.api.*;

public class GQTest {

	static GenericQueue<Integer> queue;
	
	@BeforeEach
	public void queueInitials() {
		queue = new GenericQueue<Integer>(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
	}
	
	@Test
	public void constructorTest() {
		Assertions.assertEquals(1, queue.getHead().data);
		Assertions.assertEquals(4, queue.getLength());		
	}
	
	@Test
	public void enqueueTest() {
		queue.enqueue(5);
		Assertions.assertEquals(1, queue.getHead().data);
		Assertions.assertEquals(5, queue.getLength());
	}
	
	@Test
	public void listIteratorTest() {
		/*queue.print();
		System.out.println(queue.listIterator(1).hasNext());*/
		for(int i = 0; i < 4; i++) {
			Assertions.assertEquals(i + 1, queue.listIterator(i).next());
		}
	}
	
	@Test
	public void iteratorTest() {
		Iterator<Integer> ans = queue.iterator();
		int counter = 1;
		while(ans.hasNext()) {
			Assertions.assertEquals(counter,ans.next());
			counter++;
		}
		Assertions.assertEquals(5,counter);
	}
	
	@Test
	public void descendingIteratorTest() {
		Iterator<Integer> ans = queue.descendingIterator();
		int counter = 4;
		while(ans.hasNext()) {
			Assertions.assertEquals(counter,ans.next());
			counter--;
		}
		Assertions.assertEquals(0,counter);
	}
	
	@Test
	public void testForEach() {
		for(int i : queue) {
			System.out.println(i);
		}
	}
	
	@Test
	public void removeTailTest() {
		queue.removeTail();
		Assertions.assertEquals(2, queue.removeTail());
		Assertions.assertEquals(2, queue.tail.data);
		Assertions.assertEquals(2, queue.getLength());
	}
	
	@Test
	public void dequeueTest() {
		queue.dequeue();
		Assertions.assertEquals(4, queue.tail.data);
		Assertions.assertEquals(2, queue.getHead().data);
		Assertions.assertEquals(3, queue.getLength());
	}
	
}
