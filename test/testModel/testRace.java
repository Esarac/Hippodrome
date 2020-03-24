package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistException;
import exception.InvalidAmountException;
import model.Competitor;
import model.Race;
import model.User;
import util.HashTable;

class TestRace {

	//Tested Class
	private Race race;
	
	//Scene
	private void setUpSceneEmpty(){
		this.race=new Race(100);
	}
	
	private void setUpSceneNormal() {
		this.race=new Race(100);
		race.addCompetitor("Esteban", "Nayeon");
		race.addCompetitor("Johan", "Jihyo");
		race.addCompetitor("Mateo", "Dahyun");
		race.addCompetitor("Juan", "Chaeyoung");
		race.addCompetitor("David", "Sana");
		race.addCompetitor("Samuel", "Momo");
		race.addCompetitor("Isabella", "Mina");
		race.addCompetitor("Diana", "Jeongyeon");
		race.addCompetitor("Fredy", "Tzuyu");
		race.addCompetitor("Alberto", "Somi");
		
		ArrayList<Competitor> competitors=race.getCompetitors().toArrayList();
		competitors.get(0).setSpeed(9);
		competitors.get(1).setSpeed(10);
		competitors.get(2).setSpeed(8);
		competitors.get(3).setSpeed(7);
		competitors.get(4).setSpeed(6);
		competitors.get(5).setSpeed(5);
		competitors.get(6).setSpeed(4);
		competitors.get(7).setSpeed(3);
		competitors.get(8).setSpeed(2);
		competitors.get(9).setSpeed(1);
		
		race.addUser("1000", "Nayeon", competitors.get(0), 1000);
		race.addUser("1001", "Jihyo", competitors.get(0), 1000);
		race.addUser("1002", "Dahyun", competitors.get(1), 1000);
		race.addUser("1003", "Chaeyoung", competitors.get(2), 1000);
	}
	
	//Test
	@Test
	void testAddCompetitor() {
		setUpSceneEmpty();
		race.addCompetitor("Esteban", "Nayeon");
		race.addCompetitor("Johan", "Jihyo");
		race.addCompetitor("Mateo", "Dahyun");
		race.addCompetitor("Juan", "Chaeyoung");
		race.addCompetitor("David", "Sana");
		race.addCompetitor("Samuel", "Momo");
		race.addCompetitor("Isabella", "Mina");
		race.addCompetitor("Diana", "Jeongyeon");
		race.addCompetitor("Fredy", "Tzuyu");
		race.addCompetitor("Alberto", "Somi");
		assertThrows(InvalidAmountException.class,()->{
			race.addCompetitor("Jose", "Miranda Cosgroves");
		});
		
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Nayeon");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Jihyo");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Dahyun");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Chaeyoung");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Sana");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Momo");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Mina");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Jeongyeon");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Tzuyu");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Somi");
		
	}

	@Test
	void testAddUser() {
		setUpSceneEmpty();
		race.addUser("1000", "Nayeon", new Competitor("Esteban", "Nayeon"), 1000);
		race.addUser("1001", "Jihyo", new Competitor("Esteban", "Nayeon"), 1000);
		race.addUser("1002", "Dahyun", new Competitor("Esteban", "Nayeon"), 1000);
		race.addUser("1003", "Chaeyoung", new Competitor("Esteban", "Nayeon"), 1000);
		assertThrows(AlreadyExistException.class,()->{
			race.addUser("1000", "Nayeon", new Competitor("Esteban", "Nayeon"), 1000);
		});
		
		assertEquals(race.searchUser("1000").getName(), "Nayeon");
		assertEquals(race.searchUser("1001").getName(), "Jihyo");
		assertEquals(race.searchUser("1002").getName(), "Dahyun");
		assertEquals(race.searchUser("1003").getName(), "Chaeyoung");
	}
	
	@Test
	void testRaceSimulator() {
		setUpSceneEmpty();
		assertThrows(InvalidAmountException.class, ()->{
			race.raceSimulator();
		});
		
		setUpSceneNormal();
		assertEquals(race.raceSimulator().getHorse(), "Jihyo");
		
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Somi");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Tzuyu");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Jeongyeon");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Mina");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Momo");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Sana");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Chaeyoung");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Dahyun");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Nayeon");
		assertEquals(race.getCompetitors().dequeue().getHorse(), "Jihyo");
		
	}
	
	@Test
	void testSearchUser() {
		setUpSceneEmpty();
		assertNull(race.searchUser("1000"));
		
		setUpSceneNormal();
		assertEquals(race.searchUser("1000").getName(), "Nayeon");
	}
}
