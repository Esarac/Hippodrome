package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.EmptyListException;
import util.Queue;
import util.Stack;

class TestStack {
	
	private Stack<Integer> sk = new Stack<>();
	
	private void setUpScene() {
		
		sk = new Stack<>();
		
		for(int i = 1; i < 6; i++) {
			sk.push(i);
		}
	}
	
	private void setUpScene1() {
		
		sk = new Stack<>();
		
		sk.push(9);
		sk.push(90);
		sk.push(2);
		sk.push(76);
		sk.push(34);
	}
	
	private void setUpScene2() {
		
		sk = new Stack<>();
	}
	
	@Test
	void testStack() {
		
		setUpScene();
		
		assertEquals(5, sk.size());
		
		assertEquals(5, sk.pop());
		assertEquals(4, sk.size());
		
		assertEquals(4, sk.pop());
		assertEquals(3, sk.size());
		
		assertEquals(3, sk.pop());
		assertEquals(2, sk.size());
		
		assertEquals(2, sk.pop());
		assertEquals(1, sk.size());
		
		assertEquals(1, sk.pop());
		assertEquals(0, sk.size());
		
	}
	
	@Test
	void testStack1() {
		
		setUpScene1();
		
		assertEquals(5, sk.size());
		
		assertEquals(34, sk.pop());
		assertEquals(4, sk.size());
		
		assertEquals(76, sk.pop());
		assertEquals(3, sk.size());
		
		assertEquals(2, sk.pop());
		assertEquals(2, sk.size());
		
		assertEquals(90, sk.pop());
		assertEquals(1, sk.size());
		
		assertEquals(9, sk.pop());
		assertEquals(0, sk.size());
		
	}
	
	@Test
	void testStack2() {
		
		setUpScene2();
		
		assertEquals(0, sk.size());
		
		sk.push(2);
		
		assertEquals(1, sk.size());
		
		sk.push(3);
		sk.push(9);
		
		assertEquals(3, sk.size());
		
		sk.pop();
		
		assertEquals(2, sk.size());
		
		assertEquals(3, sk.peek());
		assertEquals(2, sk.size());
		
		sk.pop();
		
		assertEquals(2, sk.peek());
		assertEquals(1, sk.size());
		
		sk.pop();
		
		assertThrows(EmptyListException.class, () -> { sk.pop();});
		assertThrows(EmptyListException.class, () -> { sk.peek();});
	}
}
