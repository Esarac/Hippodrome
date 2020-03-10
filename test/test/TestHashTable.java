package test;

import model.User;
import util.HashTable;
import util.Node;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestHashTable {

	//Tested Class
	private HashTable<User, String> hashTable;
	
	//Scene
	private void setUpSceneEmpty(){
		this.hashTable=new HashTable<User, String>(10);
	}
	
	private void setUpSceneNormal() {
		this.hashTable=new HashTable<User, String>(10);
		hashTable.add(new User("220"));
		hashTable.add(new User("124"));
		hashTable.add(new User("983"));
		hashTable.add(new User("22:"));
	}
	
	//Test
	@Test
	void testHash() {
		setUpSceneEmpty();
		assertEquals(hashTable.hash("220"), 6);
		assertEquals(hashTable.hash("213"), 9);
		assertEquals(hashTable.hash("326"), 2);
		assertEquals(hashTable.hash("659"), 5);
	}

	@Test
	void testAdd() {
		setUpSceneEmpty();
		hashTable.add(new User("220"));
		hashTable.add(new User("124"));
		hashTable.add(new User("983"));
		hashTable.add(new User("22:"));
		
		Node<User>[] table=hashTable.getTable();
		
		assertEquals(table[6].getElement().getId(), "22:");
		assertEquals(table[0].getElement().getId(), "124");
		assertEquals(table[9].getElement().getId(), "983");
		assertEquals(table[6].getNext().getElement().getId(), "220");
		
	}
	
	@Test
	void testGet(){
		setUpSceneNormal();
		
		assertEquals(hashTable.get("220").getId(),"220");
		assertEquals(hashTable.get("124").getId(),"124");
		assertEquals(hashTable.get("983").getId(),"983");
		assertEquals(hashTable.get("22:").getId(),"22:");
		assertNull(hashTable.get("793"));
		
	}
	
	@Test
	void testDelete(){
		setUpSceneNormal();
		
		assertTrue(hashTable.remove("220"));
		assertTrue(hashTable.remove("124"));
		assertFalse(hashTable.remove("793"));
		
		Node<User>[] table=hashTable.getTable();
		
		assertNull(table[6].getNext());
		assertNull(table[0]);
		
	}
	
	@Test
	void testQuantity(){
		setUpSceneEmpty();
		assertEquals(hashTable.quantity(),0);
		
		setUpSceneNormal();
		assertEquals(hashTable.quantity(),4);
		
		hashTable.remove("220");
		
		assertEquals(hashTable.quantity(),3);
	}
	
}
