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
		
		qe.enqueue(9);
		qe.enqueue(90);
		qe.enqueue(2);
		qe.enqueue(76);
		qe.enqueue(34);
		qe.enqueue(1);
	}
	
	private void setUpScene3() {
		
		qe = new Queue<>();
		
		qe.enqueue(3);
		qe.enqueue(2);
		qe.enqueue(1);
	}
	
	private void setUpScene4() {
		
		qe = new Queue<>();
	}

	@Test
	void testEnqueue() {
		
		setUpScene();
		
		assertEquals(5, qe.size());
		
		assertEquals(1, qe.dequeue());
		assertEquals(4, qe.size());
		
		assertEquals(2, qe.dequeue());
		assertEquals(3, qe.size());
		
		assertEquals(3, qe.dequeue());
		assertEquals(2, qe.size());
		
		assertEquals(4, qe.dequeue());
		assertEquals(1, qe.size());
		
		assertEquals(5, qe.dequeue());
		assertEquals(0, qe.size());
	}
	
	@Test
	void testEnqueue2() {
		
		setUpScene2();
		
		assertEquals(6, qe.size());
		
		assertEquals(9, qe.dequeue());
		assertEquals(5, qe.size());
		
		assertEquals(90, qe.dequeue());
		assertEquals(4, qe.size());
		
		assertEquals(2, qe.dequeue());
		assertEquals(3, qe.size());
		
		assertEquals(76, qe.dequeue());
		assertEquals(2, qe.size());
		
		assertEquals(34, qe.dequeue());
		assertEquals(1, qe.size());
		
		assertEquals(1, qe.dequeue());
		assertEquals(0, qe.size());
	}
	
	@Test
	void testEnqueue3() {
		
		setUpScene3();
		
		assertEquals(3, qe.size());
		qe.enqueue(9);
		assertEquals(4, qe.size());
		
		qe.enqueue(2);
		qe.enqueue(3);
		
		assertEquals(6, qe.size());
		
		qe.dequeue();
		qe.enqueue(3);
		
		assertEquals(6, qe.size());
	}
	
	@Test
	void testEnqueue4() {
		
		setUpScene4();
		
		assertTrue(qe.isEmpty());
		
		qe.enqueue(3);
		
		assertFalse(qe.isEmpty());
		
		assertEquals(3, qe.peek());
		assertEquals(1, qe.size());
		
		qe.dequeue();
		
		assertThrows(EmptyListException.class, () -> { qe.dequeue();});
		assertThrows(EmptyListException.class, () -> { qe.peek();});
		
	}
}
