package model;

import java.util.ArrayList;
import java.util.Collections;
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
			Competitor competitor=new Competitor(rider, horse);
			competitors.enqueue(competitor);
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
				sAux.peek().setSpeed(Competitor.randomSpeed());
				qAux.enqueue(sAux.pop());
			}
			competitors=qAux;
			
		}
		else{
			throw new InvalidAmountException("ERROR. There cannot be more than 10 competitors and less than 7 competitors.");
		}
		
		return winner;
	}
	
	public void clearUsers(){
		users=new HashTable<User>(users.getTable().length);
	}
	
	public User searchUser(String id) {
		return users.get(id);
	}
	
	public ArrayList<Competitor> getPodium(){
		ArrayList<Competitor> competitors=this.competitors.toArrayList();
		ArrayList<Competitor> podium=new ArrayList<Competitor>();
		
		podium.add(competitors.get(competitors.size()-1));
		podium.add(competitors.get(competitors.size()-2));
		podium.add(competitors.get(competitors.size()-3));
		
		return podium;
	}
	
	public Queue<Competitor> getCompetitors() {
		return competitors;
	}
}
