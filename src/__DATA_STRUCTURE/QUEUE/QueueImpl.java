package __DATA_STRUCTURE.QUEUE;

import __DATA_STRUCTURE.Node;

public class QueueImpl<T> implements Queue<T> {
	Node<T> first;
	Node<T> last;
	int size;

	public QueueImpl() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(T item) {
		Node<T> node = new Node<>(item);

		if (last != null) {
			last.linkNode(node);
		}
		if (first == null) {
			first = node;
		}
		last = node;
		size++;
	}

	@Override
	public T poll() {
		if (size == 0) {
			return null;
		}

		T item = first.getData();
		first = first.getNextNode();

		if (first == null) {
			last = null;
		}
		size--;
		return item;
	}

	@Override
	public T peek() {
		return first.getData();
	}

	@Override
	public void clear() {
		while (size != 0) {
			poll();
		}
	}

	@Override
	public int size() {
		return size;
	}

}
