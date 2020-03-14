package model;

import util.HashTable;
import util.Queue;

public class Race {

	//Attributes
	private Queue<Competitor> competitors;
	private HashTable<User> users;
	
	//Constructor
	private Race(int usersSize){
		this.competitors = new Queue<>();
		this.users=new HashTable<User>(usersSize);
	}
	
	//Methods
	public void addCompetitor(String rider, String horse) {
		
		competitors.enqueue(new Competitor(rider, horse));
		
	}
	
	public void addUser(String id, String name, String rider, String horse, double betMoney) {
		
		users.add(new User(id, name, new Competitor(rider, horse), betMoney));
	}
	
}
