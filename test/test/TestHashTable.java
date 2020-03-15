package test;

import model.Competitor;
import model.User;
import util.HashTable;
import util.Node;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistException;

class TestHashTable {

	//Tested Class
	private HashTable<User> hashTable;
	
	//Scene
	private void setUpSceneEmpty(){
		this.hashTable=new HashTable<User>(10);
	}
	
	private void setUpSceneNormal() {
		this.hashTable=new HashTable<User>(10);
		hashTable.add(new User("220", "Esteban Ariza",new Competitor("Nayeon", "Tzuyu"), 1000));
		hashTable.add(new User("213", "Mateo Valdes",new Competitor("Sana", "Momo"), 1000));
		hashTable.add(new User("326", "Johan Giraldo",new Competitor("Dahyun", "Jihyo"), 1000));
		hashTable.add(new User("659", "Juan Ossa",new Competitor("Chaeyoung", "Jeongyeon"), 1000));
	}
	
	//Test
	@Test
	void testHash() {
		setUpSceneEmpty();
		assertEquals(hashTable.hash("111"), 7);
		assertEquals(hashTable.hash("1111"), 0);
		assertEquals(hashTable.hash("11111"), 0);
	}

	@Test
	void testAdd() {
		setUpSceneEmpty();
		hashTable.add(new User("220", "Esteban Ariza",new Competitor("Nayeon", "Tzuyu"), 1000));
		hashTable.add(new User("213", "Mateo Valdes",new Competitor("Sana", "Momo"), 1000));
		hashTable.add(new User("326", "Johan Giraldo",new Competitor("Dahyun", "Jihyo"), 1000));
		hashTable.add(new User("659", "Juan Ossa",new Competitor("Chaeyoung", "Jeongyon"), 1000));
		assertThrows(AlreadyExistException.class, ()->{
			hashTable.add(new User("220", "Esteban Ariza",new Competitor("Nayeon", "Tzuyu"), 1000));
		});
		
		Node<User>[] table=hashTable.getTable();
		
		assertEquals(table[2].getElement().getId(), "220");
		assertEquals(table[6].getElement().getId(), "659");
		assertEquals(table[7].getElement().getId(), "326");
		assertEquals(table[6].getNext().getElement().getId(), "213");
		
	}
	
	@Test
	void testGet(){
		setUpSceneNormal();
		
		assertEquals(hashTable.get("220").getId(),"220");
		assertEquals(hashTable.get("659").getId(),"659");
		assertEquals(hashTable.get("326").getId(),"326");
		assertEquals(hashTable.get("213").getId(),"213");
		assertNull(hashTable.get("793"));
		
	}
	
	@Test
	void testDelete(){
		setUpSceneNormal();
		
		assertTrue(hashTable.remove("220"));
		assertTrue(hashTable.remove("659"));
		assertFalse(hashTable.remove("783"));
		
		Node<User>[] table=hashTable.getTable();
		
		assertEquals(table[6].getElement().getKey(), "213");
		assertNull(table[6].getNext());
		assertNull(table[2]);
		
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
