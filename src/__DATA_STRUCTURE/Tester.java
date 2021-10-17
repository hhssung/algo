package __DATA_STRUCTURE;

import __DATA_STRUCTURE.STACK.Stack;
import __DATA_STRUCTURE.STACK.StackImpl;

public class Tester {
	public static void main(String[] args) {	
		stackTest();
		//
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
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
