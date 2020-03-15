package model;

import java.util.ArrayList;

import exception.InvalidAmountException;
import util.HashTable;
import util.Queue;

public class Race {

	//Attributes
	private Queue<Competitor> competitorQueue;
	private HashTable<User> users;
	private ArrayList<Competitor> competitorList;
	
	//Constructor
	public Race(int usersSize){
		this.competitorQueue = new Queue<>();
		this.users=new HashTable<User>(usersSize);
		this.competitorList = new ArrayList<>();
	}
	
	//Methods
	public void addCompetitor(String rider, String horse) {
		
		int competitorAmount = competitorQueue.size();
		
		if (competitorAmount >= 10) 
			throw new InvalidAmountException("ERROR. There cannot be more than 10 competitors");
		else {
			competitorQueue.enqueue(new Competitor(rider, horse));
			competitorList.add(new Competitor(rider, horse));
		}
		
		
//		Competitor c = competitors.dequeue();
//		System.out.println(c.getHorse());
//		System.out.println(c.getRider());
		
	}
	
	public void addUser(String id, String name, String rider, String horse, double betMoney) {
		
		users.add(new User(id, name, new Competitor(rider, horse), betMoney));
	}
	
	public boolean readyToRace() {
		
		return true;	
		
	}
	
	public User searchUser(String id) {
		return users.get(id);
	}
}
