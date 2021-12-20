import java.util.Iterator;
import org.junit.jupiter.api.*;

public class GSTest {
	
	static GenericStack<Integer> stack;
	
	@BeforeEach
	public void stackInitials() {
		stack = new GenericStack<Integer>(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
	}
	
	@Test
	public void constructorTest() {
		Assertions.assertEquals(4, stack.getHead().data);
		Assertions.assertEquals(4, stack.getLength());		
	}
	
	@Test
	public void pushTest() {
		stack.push(5);
		Assertions.assertEquals(5, stack.getHead().data);
		Assertions.assertEquals(5, stack.getLength());
	}
	
	@Test
	public void listIteratorTest() {
		for(int i = 0; i > 4; i++) {
			Assertions.assertEquals(4 - i + 1, stack.listIterator(i).next());
		}
	}
	
	@Test
	public void iteratorTest() {
		Iterator<Integer> ans = stack.iterator();
		int counter = 4;
		while(ans.hasNext()) {
			Assertions.assertEquals(counter,ans.next());
			counter--;
		}
		Assertions.assertEquals(0,counter);
	}
	
	@Test
	public void descendingIteratorTest() {
		Iterator<Integer> ans = stack.descendingIterator();
		int counter = 1;
		while(ans.hasNext()) {
			Assertions.assertEquals(counter,ans.next());
			counter++;
		}
		Assertions.assertEquals(2,counter);
	}
	
	@Test
	public void testForEach() {
		for(int i : stack) {
			System.out.println(i);
		}
	}
	
	
	@Test
	public void removeTailTest() {
		stack.removeTail();
		Assertions.assertEquals(3, stack.removeTail());
		Assertions.assertEquals(3, stack.tail.data);
		Assertions.assertEquals(2, stack.getLength());
	}
	
	@Test
	public void popTest() {
		stack.pop();
		Assertions.assertEquals(1, stack.tail.data);
		Assertions.assertEquals(3, stack.getHead().data);
		Assertions.assertEquals(3, stack.getLength());
	}
	
}
