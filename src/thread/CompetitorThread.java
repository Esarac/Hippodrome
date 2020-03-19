package thread;

import controller.ViewController;
import javafx.application.Platform;
import model.Competitor;

public class CompetitorThread extends Thread{

	//Attributes
	private Competitor competitor;
	private int rail;
	private ViewController controller;
	
	//Constructor
	public CompetitorThread(Competitor competitor,int rail, ViewController controller){
		this.competitor=competitor;
		this.rail=rail;
		this.controller=controller;
		setDaemon(true);
	}
	
	//Methods
	public void run() {
		
		for(int i=0; i<=700; i++ ){
			
			int pos=i;
			Runnable move=new Runnable() {
				public void run() {controller.updateRace(rail, pos);}
			};
			Platform.runLater(move);
			
			try {
				sleep((long)(100/competitor.getSpeed()));
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
