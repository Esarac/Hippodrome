package model;

public class Competitor {

	//Attributes
	private String horse;
	private String rider;
	private double horseSpeed;
	
	//Constructor
	public Competitor(String horse, String rider) {
		this.horse=horse;
		this.rider=rider;
		horseSpeed = Math.random();
	}
	
	//Methods
	public String getHorse() {
		return horse;
	}
	
	public String getRider() {
		return rider;
	}
}
