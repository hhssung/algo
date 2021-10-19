package __DATA_STRUCTURE;

import __DATA_STRUCTURE.QUEUE.Queue;
import __DATA_STRUCTURE.QUEUE.QueueImpl;
import __DATA_STRUCTURE.STACK.Stack;
import __DATA_STRUCTURE.STACK.StackImpl;

public class Tester {
	public static void main(String[] args) {
		// stackTest();
		queueTest();
	}

	private static void queueTest() {
		Queue<String> queue = new QueueImpl<>();
		System.out.println(queue.size());
		queue.add("test_1");
		queue.add("test_2");
		queue.add("test_3");
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		System.out.println(queue.peek());
		System.out.println(queue.size());
		queue.clear();
		queue.add("test_4");
		queue.add("test_5");
		queue.add("test_6");
		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
	}

	private static void stackTest() {
		Stack<String> stack = new StackImpl<>();
		System.out.println(stack.size());
		stack.push("test_1");
		stack.push("test_2");
		stack.push("test_3");
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.size());
		stack.clear();
		stack.push("test_4");
		stack.push("test_5");
		stack.push("test_6");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
