package model;

public class Clock {

	private int hour;
	private int min;
	private int sec;
	
	public Clock() {
		hour = 0;
		min = 0;
		sec = 0;
		
	}
	
	public void update() {
		sec++;
		
		if (sec > 59) {
			min++;
			sec = 0;
		}
		
		if (min > 59) {
			hour++;
			min = 0;
		}
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}
	
	
	
}
