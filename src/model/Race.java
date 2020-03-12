package model;

import util.HashTable;

public class Race {

	//Attributes
	private Competitor[] competitors;
	private HashTable<User> users;
	
	//Constructor
	private Race(int competitorsSize, int usersSize){
		this.competitors=new Competitor[competitorsSize];
		this.users=new HashTable<User>(usersSize);
	}
	
	//Methods
	
	
}
