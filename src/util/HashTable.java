package util;

public class HashTable<T extends Hashable<String>> {

	private Node<T>[] items;
	
	public HashTable(int arraySize) {
		
		this.items=new Node[arraySize];
		
	}
	
	public int hash(String key){
		
		int dataSize=key.length();
		
		int last=key.charAt(dataSize-1)-32;
		
		return last%items.length;
		
	}
	
	public void add(T element){
		
		int index=hash(element.getKey());
		Node<T> node=new Node<T>(element);
		
//		node.setNextNode(items[index]);
		items[index]=node;
		
	}
	
	public T get(String key){
		
		int index=hash(key);
		
		Node<T> actualNode=items[index];
		while((actualNode!=null) && (!key.equals(actualNode.getElement().getKey()) ) ){
			actualNode=actualNode.getNext();
		}
		
		T element=null;
		if(actualNode!=null){
			element=actualNode.getElement();
		}
		
		return element;
	}
	
	public void remove(String key){
		int index=hash(key);
		
		Node<T> prevNode=null;
		Node<T> actualNode=items[index];
		
		while((actualNode!=null) && (!key.equals(actualNode.getElement().getKey()) ) ){
			prevNode=actualNode;
			actualNode=actualNode.getNext();
		}
		
		if(actualNode!=null) {
			if(prevNode==null){
				items[index]=actualNode.getNext();
			}
			else{
				prevNode.setNext(actualNode.getNext());
			}
		}
		
	}
	
}
