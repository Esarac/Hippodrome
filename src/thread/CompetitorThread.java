package thread;

import controller.ViewController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import model.Competitor;

public class CompetitorThread extends Thread{

	//Attributes
	private Competitor competitor;
	private int rail;
	private ViewController controller;
	private Image img;
	
	//Constructor
	public CompetitorThread(Competitor competitor,int rail, ViewController controller){
		this.competitor=competitor;
		this.rail=rail;
		this.controller=controller;
		
		Image img=null;
		
		String name=competitor.getHorse();
		int value=0;
		for(int j=0;j<name.length();j++){
			value+=name.charAt(j);
		}
		value%=6;
		
		switch(value) {
			case 0: img=new Image("file:med/pony/RainbowDash.gif"); break;
			case 1:	img=new Image("file:med/pony/PinkiePie.gif"); break;
			case 2: img=new Image("file:med/pony/AppleJack.gif"); break;
			case 3: img=new Image("file:med/pony/TwilightSparkle.gif"); break;
			case 4: img=new Image("file:med/pony/FlutterShy.gif"); break;
			case 5: img=new Image("file:med/pony/Rarity.gif"); break;
		}
		
		this.img=img;
		setDaemon(true);
	}
	
	//Methods
	public void run() {
		
		for(int i=0; i<=900; i++ ){
			
			int pos=i;
			Runnable move=new Runnable() {
				public void run() {controller.updateRace(rail, pos, competitor+"",img);}
			};
			Platform.runLater(move);
			
			try {
				sleep((long)(110-competitor.getSpeed()));
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Runnable end=new Runnable() {
			public void run() {controller.loadRaceEnd();}
		};
		Platform.runLater(end);
		
	}
	
}
