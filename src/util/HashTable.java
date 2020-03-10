package util;

public class HashTable<T extends Hashable<K>, K> implements InterfaceHashTable<T, K>{

	private Node<T>[] table;
	
	public HashTable(int arraySize) {
		
		this.table=new Node[arraySize];
		
	}
	
	public int hash(K key){
		
		String sKey=key.toString();
		int dataSize=sKey.length();
		
		int last=sKey.charAt(dataSize-1)-32;
		
		return last%table.length;
		
	}
	
	public void add(T element){
		
		int index=hash(element.getKey());
		Node<T> node=new Node<T>(element);
		
		node.setNext(table[index]);
		table[index]=node;
		
	}
	
	public T get(K key){
		
		int index=hash(key);
		
		Node<T> actualNode=table[index];
		while((actualNode!=null) && (!key.equals(actualNode.getElement().getKey()) ) ){
			actualNode=actualNode.getNext();
		}
		
		T element=null;
		if(actualNode!=null){
			element=actualNode.getElement();
		}
		
		return element;
	}
	
	public boolean remove(K key){
		int index=hash(key);
		
		Node<T> prevNode=null;
		Node<T> actualNode=table[index];
		
		while((actualNode!=null) && (!key.equals(actualNode.getElement().getKey()) ) ){
			prevNode=actualNode;
			actualNode=actualNode.getNext();
		}
		
		boolean found=true;
		if(actualNode!=null) {
			if(prevNode==null){
				table[index]=actualNode.getNext();
			}
			else{
				prevNode.setNext(actualNode.getNext());
			}
		}
		else{
			found=false;
		}
		
		return found;
	}
	
	public int quantity(){
		int sum=0;
		
		for(Node<T> actualNode: table){
			while(actualNode!=null){
				actualNode=actualNode.getNext();
				sum++;
			}
		}
		
		return sum;
	}
	
	public Node<T>[] getTable(){
		return table;
	}
	
}
