package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import util.Stack;

import exception.InvalidAmountException;
import util.HashTable;
import util.Queue;

public class Race {

	//Attributes
	private Queue<Competitor> competitors;
	private HashTable<User> users;
	
	//Constructor
	public Race(int usersSize){
		this.competitors = new Queue<>();
		this.users=new HashTable<User>(usersSize);
	}
	
	//Methods
	public void addCompetitor(String rider, String horse) {
		
		int competitorAmount = competitors.size();
		if (competitorAmount >= 10) 
			throw new InvalidAmountException("ERROR. There cannot be more than 10 competitors");
		else {
			competitors.enqueue(new Competitor(rider, horse));
		}
		
	}
	
	public void addUser(String id, String name, Competitor bet, double betMoney) {
		users.add(new User(id, name, bet, betMoney));
	}
	
	public  Competitor raceSimulator(){
		Competitor winner=null;
		
		if((7<=competitors.size()) && (competitors.size()<=10)){
			
			ArrayList<Competitor> list=competitors.toArrayList();
			Collections.sort(list);
			winner=list.get(0);
			
			Stack<Competitor> sAux=new Stack<Competitor>();
			for(int i=0; i<list.size(); i++){
				sAux.push(list.get(i));
			}
			
			Queue<Competitor> qAux=new Queue<Competitor>();
			while(!sAux.isEmpty()){
				qAux.enqueue(sAux.pop());
			}
			competitors=qAux;
			
		}
		else{
			throw new InvalidAmountException("ERROR. There cannot be more than 10 competitors and less than 7 competitors.");
		}
		
		return winner;
	}
	
	public User searchUser(String id) {
		return users.get(id);
	}
	
	public Queue<Competitor> getCompetitors() {
		return competitors;
	}
}
