package util;

public interface InterfaceHashTable<T extends Hashable<K>, K> {

	public int hash(K key);
	public void add(T element);
	public T get(K key);
	public boolean remove(K key);
	public int quantity();
	
}
