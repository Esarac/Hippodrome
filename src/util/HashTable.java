package util;

public class HashTable<T extends Hashable<String>> {

	private Node<T>[] table;
	
	public HashTable(int arraySize) {
		
		this.table=new Node[arraySize];
		
	}
	
	public int hash(String key){
		
		int dataSize=key.length();
		
		int last=key.charAt(dataSize-1)-32;
		
		return last%table.length;
		
	}
	
	public void add(T element){
		
		int index=hash(element.getKey());
		Node<T> node=new Node<T>(element);
		
		node.setNext(table[index]);
		table[index]=node;
		
	}
	
	public T get(String key){
		
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
	
	public boolean remove(String key){
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
	
	public Node<T>[] getTable(){
		return table;
	}
	
}
