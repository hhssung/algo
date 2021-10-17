package __DATA_STRUCTURE;

public class Node<T> {
	private T item;
	private Node<T> node;	

	public Node(T item) {
		this.item = item;
		this.node = null;
	}

	public void linkNode(Node<T> node) {
		this.node = node;
	}

	public T getData() {
		return this.item;
	}

	public Node<T> getNextNode() {
		return this.node;
	}
}
