package testModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Competitor;

class TestCompetitor {
	
	//Test
	@Test
	void testRandomSpeed() {
		assertNotEquals(Competitor.randomSpeed(),Competitor.randomSpeed());
		double speed=Competitor.randomSpeed();
		assertTrue((speed>=80) && (speed<100));
	}

}
