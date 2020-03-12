package util;

public interface InterfaceHashTable<T extends Hashable> {

	public int hash(String key);
	public void add(T element);
	public T get(String key);
	public boolean remove(String key);
	public int quantity();
	
}
