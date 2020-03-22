package model;

import java.util.Comparator;

public class Competitor implements Comparable<Competitor>{

	//Attributes
	private String horse;
	private String rider;
	private double speed;
	
	//Constructor
	public Competitor(String rider, String horse) {
		this.horse=horse;
		this.rider=rider;
		speed = randomSpeed();
		
	}
	
	//Methods
	public int compareTo(Competitor competitor) {
		return (int) ((competitor.speed*100000)-(speed*100000));
	}
	
	public static double randomSpeed(){
		return 80+(Math.random() * 20);
	}
	
	//Set
	public void setSpeed(double speed) {
		this.speed=speed;
	}
	
	//Get
	public String getHorse() {
		return horse;
	}
	
	public String getRider() {
		return rider;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public String toString(){
		return rider+" - "+horse;
	}
	
}
