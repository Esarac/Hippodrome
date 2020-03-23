package testUtil;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exception.EmptyListException;
import util.Queue;

class TestQueue {
	
	private Queue<Integer> qe;
	
	private void setUpScene() {
		
		qe = new Queue<>();
		
		for(int i = 1; i < 6; i++) {
			qe.enqueue(i);
		}
	}
	
	private void setUpScene2() {
		
		qe = new Queue<>();
	}

	@Test
	void testDequeue() {
		
		setUpScene();
		
		assertEquals(1, qe.dequeue());
		assertEquals(2, qe.dequeue());
		assertEquals(3, qe.dequeue());
		assertEquals(4, qe.dequeue());
		assertEquals(5, qe.dequeue());
		
		assertThrows(EmptyListException.class, () -> { qe.dequeue();});
	}
	
	@Test
	void testSize() {
		
		setUpScene();
		
		assertEquals(5, qe.size());
		
		qe.dequeue();
		assertEquals(4, qe.size());
		
		qe.enqueue(3);
		assertEquals(5, qe.size());
		
		qe.dequeue();
		assertEquals(4, qe.size());
		
		qe.dequeue();
		assertEquals(3, qe.size());
		
		qe.dequeue();
		assertEquals(2, qe.size());
		
		qe.enqueue(6);
		assertEquals(3, qe.size());
		
		qe.enqueue(7);
		assertEquals(4, qe.size());
		
		qe.dequeue();
		qe.dequeue();
		
		qe.dequeue();
		assertEquals(1, qe.size());
		
		qe.dequeue();
		assertEquals(0, qe.size());
	}
	
	@Test
	void testEnqueue() {
		
		setUpScene2();
		
		qe.enqueue(6);
		qe.enqueue(5);
		qe.enqueue(6);
		qe.enqueue(4);
		qe.enqueue(1);
		
		assertEquals(5, qe.size());
		
		assertEquals(6, qe.dequeue());
		assertEquals(5, qe.dequeue());
		assertEquals(6, qe.dequeue());
		assertEquals(4, qe.dequeue());
		assertEquals(1, qe.dequeue());
	}
	
	@Test
	void testPeek() {
		
		setUpScene2();
		
		qe.enqueue(3);
		
		assertEquals(3, qe.peek());
		assertEquals(1, qe.size());
		
		qe.enqueue(9);
		
		assertEquals(3, qe.peek());
		assertEquals(2, qe.size());
		
		qe.dequeue();
		
		assertEquals(9, qe.peek());
		
		qe.dequeue();
		
		assertEquals(0, qe.size());
	
		assertThrows(EmptyListException.class, () -> { qe.peek();});
	}
	
	@Test
	void testIsEmpty() {
		
		setUpScene2();
		
		assertTrue(qe.isEmpty());
		
		qe.enqueue(2);		
		assertFalse(qe.isEmpty());
		
		qe.enqueue(4);
		assertFalse(qe.isEmpty());
		
		qe.dequeue();
		qe.dequeue();
		
		assertTrue(qe.isEmpty());
	}
}