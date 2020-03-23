package testUtil;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.EmptyListException;
import util.Stack;

class TestStack {
	
	private Stack<Integer> sk = new Stack<>();
	
	private void setUpScene() {
		
		sk = new Stack<>();
		
		for(int i = 1; i < 6; i++) {
			sk.push(i);
		}
	}
	
	private void setUpScene2() {
		
		sk = new Stack<>();
	}
	
	@Test
	void testPop() {
		
		setUpScene();
		
		assertEquals(5, sk.size());
		
		assertEquals(5, sk.pop());
		assertEquals(4, sk.pop());
		assertEquals(3, sk.pop());
		assertEquals(2, sk.pop());	
		assertEquals(1, sk.pop());
		
		assertEquals(0, sk.size());
		
		assertThrows(EmptyListException.class, () -> { sk.pop();});
	}
	
	@Test
	void testPush() {
		
		setUpScene2();
		
		sk.push(2);
		sk.push(5);
		sk.push(6);
		sk.push(7);
		sk.push(3);
		
		assertEquals(5, sk.size());
		
		assertEquals(3, sk.pop());
		assertEquals(7, sk.pop());
		assertEquals(6, sk.pop());
		assertEquals(5, sk.pop());
		assertEquals(2, sk.pop());
	}
	
	@Test
	void testSize() {
		
		setUpScene2();
		
		assertEquals(0, sk.size());
		
		sk.push(1);
		assertEquals(1, sk.size());
		
		sk.push(2);
		assertEquals(2, sk.size());
		
		sk.push(3);
		sk.push(4);
		assertEquals(4, sk.size());
		
		sk.pop();
		assertEquals(3, sk.size());
		
		sk.pop();
		sk.pop();
		assertEquals(1, sk.size());
		
		sk.push(4);
		assertEquals(2, sk.size());
		
		sk.pop();
		sk.pop();
		assertEquals(0, sk.size());
	}
	
	@Test
	void testPeek() {
		
		setUpScene2();
		
		sk.push(2);
		sk.push(5);
		sk.push(6);
		sk.push(7);
		sk.push(3);
		
		assertEquals(5, sk.size());
		assertEquals(3, sk.peek());
		assertEquals(5, sk.size());
		
		sk.pop();
		sk.pop();
		
		assertEquals(3, sk.size());
		assertEquals(6, sk.peek());
		assertEquals(3, sk.size());
		
		sk.pop();
		sk.pop();
		sk.pop();
		
		assertEquals(0, sk.size());
		
		assertThrows(EmptyListException.class, () -> { sk.peek();});
	}
	
	@Test
	void isEmpty() {
		
		setUpScene2();
		
		assertTrue(sk.isEmpty());
		
		sk.push(1);
		
		assertFalse(sk.isEmpty());
		
		sk.push(2);
		sk.push(3);
		
		assertFalse(sk.isEmpty());
		
		sk.pop();
		sk.pop();
		sk.pop();
		
		assertTrue(sk.isEmpty());
	}
}
