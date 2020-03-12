package util;

import exception.AlreadyExistException;

public class HashTable<T extends Hashable> implements InterfaceHashTable<T>{

	private Node<T>[] table;
	
	public HashTable(int arraySize) {
		
		this.table=new Node[arraySize];
		
	}
	
	public int hash(String key){
		
		int sum=0;
		
		for(int i=(key.length()-4); i<key.length(); i++){
			int j=i;
			
			if(key.length()>4){
				j=i-(key.length()-4);
			}
			
			if(i>=0){
				sum+=(key.charAt(i)*Math.pow(3, j));
			}
		}
		
		return sum%table.length;
		
	}
	
	public void add(T element){
		
		if(get(element.getKey())==null){
			int index=hash(element.getKey());
			Node<T> node=new Node<T>(element);
			
			node.setNext(table[index]);
			table[index]=node;
		}
		else{
			throw new AlreadyExistException();
		}
		
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
