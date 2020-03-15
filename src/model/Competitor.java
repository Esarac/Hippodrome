package model;

public class Competitor {

	//Attributes
	private String horse;
	private String rider;
	private double horseSpeed;
	private double runDistance;
	
	//Constructor
	public Competitor(String horse, String rider) {
		this.horse=horse;
		this.rider=rider;
		horseSpeed = Math.random() * 10;
		runDistance = 0;
	}
	
	//Methods
	public String getHorse() {
		return horse;
	}
	
	public String getRider() {
		return rider;
	}
	
	public double getRunDistance() {
		return runDistance;
	}
	
	public void updatePos() {
		runDistance += horseSpeed;
	}
}
