package __DATA_STRUCTURE.STACK;

public interface Stack <T>{
	public boolean isEmpty();
	public void push(T node);
	public T pop();
	public T peek();
	public void clear();
	public int size();
}
