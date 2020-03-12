package model;

import util.Hashable;

public class User implements Hashable{
	
	//Attributes
	private String id;
	private String name;
	private Competitor bet;
	private double betMoney;
	
	//Constructor
	public User(String id, String name, Competitor bet, double betMoney) {
		this.id=id;
		this.name=name;
		this.bet=bet;
		this.betMoney=betMoney;
	}
	
	//Methods
	public String getKey() {
		return id;
	}

	public String getId() {
		return id;
	}
	
}
