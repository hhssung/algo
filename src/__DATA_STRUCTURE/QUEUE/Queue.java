package __DATA_STRUCTURE.QUEUE;

public interface Queue <T>{
	public boolean isEmpty();
	public void add(T item);
	public T poll();
	public T peek();
	public void clear();
	public int size();
}
