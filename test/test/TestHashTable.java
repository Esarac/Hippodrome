package test;

import model.User;
import util.HashTable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestHashTable {

	//Tested Class
	private HashTable<User> hashTable;
	
	//Scene
	private void setUpScene1(){
		this.hashTable=new HashTable<User>(20);
	}
	
	//Test
	@Test
	void testHash() {
		setUpScene1();
		assertEquals(hashTable.hash("220"), 16);
		assertEquals(hashTable.hash("223"), 19);
		assertEquals(hashTable.hash("226"), 2);
		assertEquals(hashTable.hash("229"), 5);
	}

}
