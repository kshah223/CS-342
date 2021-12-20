

public class GLProject {

	public static void main(String[] args) {
		System.out.println("Welcome to project 1");

		GenericStack<Integer> stack = new GenericStack<Integer>(1);

		stack.push(2);
		stack.push(3);
		stack.push(4);

		stack.print();
		System.out.println();
		System.out.println("Stack Length is: " + stack.getLength());

		stack.pop();
		stack.pop();
		
		stack.print();
		System.out.println();
		System.out.println("Stack Length is: " + stack.getLength());

		GenericQueue<Integer> queue = new GenericQueue<Integer>(1);

		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);

		queue.print();
		System.out.println();
		System.out.println("Queue Length is: " + queue.getLength());

		queue.dequeue();
		queue.dequeue();
		
		queue.print();
		System.out.println();
		System.out.println("Queue Length is: " + queue.getLength());
		
	}
}
