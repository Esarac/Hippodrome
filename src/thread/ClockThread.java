package thread;

import controller.ViewController;
import javafx.application.Platform;
import model.Clock;

public class ClockThread extends Thread {

	private Clock clock;
	private ViewController vc;
	
	public ClockThread(Clock clk, ViewController vcr) {
		clock = clk;
		vc = vcr;
		setDaemon(true);
	}

	public void run() {
		
		boolean running = true;
		
		while (running) {
			
			clock.update();
			
			Platform.runLater( () -> {
				vc.updateTime();
			});
			
			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			if (clock.getMin() >= 3) {
//				running = false;
//			}
			if(clock.getSec()>3) {
				running=false;
			}
		}
	}
}