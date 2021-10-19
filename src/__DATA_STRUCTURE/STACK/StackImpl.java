package __DATA_STRUCTURE.STACK;

import __DATA_STRUCTURE.Node;

public class StackImpl<T> implements Stack<T> {
	Node<T> top; // 가장 최근의 노드
	int size;

	public StackImpl() {
		this.top = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void push(T item) {
		Node<T> tmp = new Node<>(item);
		tmp.linkNode(top);
		top = tmp;
		size++;
	}

	@Override
	public T pop() {
		T item = top.getData();
		top = top.getNextNode();
		size--;
		return item;
	}

	@Override
	public T peek() {
		return top.getData();
	}

	@Override
	public void clear() {
		while (size != 0) {
			pop();
		}
	}

	@Override
	public int size() {
		return size;
	}
}
