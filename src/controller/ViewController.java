package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Clock;
import model.Competitor;
import model.Race;
import thread.ClockThread;
import thread.CompetitorThread;

public class ViewController implements Initializable {

	private Race race;
	private Clock clock;
	private Button addUser;
	private Button newRace;
	private Label time;
	private Label l2;
	private TextField search;
	private Canvas canvas;
	
	@FXML private BorderPane root;
	@FXML private VBox vb1;
	@FXML private Button addComp;
	@FXML private Button startRace;
	@FXML private GridPane grid;
	@FXML private Pane image;
	@FXML private ListView<Label> list;
	
	private int auxFinish;
	
	//Initialize
	public void initialize(URL arg0, ResourceBundle arg1) {
		startNewRace();
		loadHorseRegistration();
	}
	
	//Load
	public void loadHorseRegistration() {

		addComp.setOnAction(e -> {
			addCompetitor();
		});
		
		startRace.setOnAction(e -> {
			loadRacePreparation();
			startRacePreparation();
		});
	}
	
	public void loadRacePreparation() {
		
		addUser = new Button("Add user");
		addUser.setOnAction(e -> {
			addUser();
		});
		
		time = new Label();
		
		clock = null;
		clock = new Clock();
		
		new ClockThread(clock, this).start();
	}
	
	public void loadRace() {
		
		newRace = new Button("Start new race");
		newRace.setOnAction(e -> {
			restart();
		});
		
		newRace.setDisable(true);
		grid.getChildren().clear();
		grid.add(newRace, 0, 0);
		search = new TextField();
		GridPane.setMargin(newRace, new Insets(0, 0, 0, 35));
		GridPane.setMargin(search, new Insets(0, 0, 0, 10));
		search.setVisible(false);
		grid.add(search, 0, 1);
		
		canvas = new Canvas(1000, 600);
		root.setCenter(canvas);
		
		ArrayList<Competitor> competitors=race.getCompetitors().toArrayList();
		for(int i=0; i<competitors.size(); i++) {
			Image img=new Image("file:med/Logo.png");
			new CompetitorThread(competitors.get(i), i, this, img).start();
		}
		
	}
	
	public void loadRaceEnd() {
		if(auxFinish>=race.getCompetitors().size()-1){
			newRace.setDisable(false);
			search.setVisible(true);
			System.out.println("Funciono");
		}
		else{
			System.out.println("llegue");
			auxFinish++;
		}
		
	}
		
	//Start
	public void startHorseRegistration() {
		
		if(!root.getChildren().contains(addComp)) {
			root.getChildren().clear();
			grid.getChildren().clear();
			grid.add(addComp, 0 , 0);
			grid.add(startRace, 0 , 1);
			list.getItems().clear();
			root.setLeft(vb1);
			root.setCenter(image);
		}
	}
	
	public void startNewRace() {
		race = null;
		race = new Race(100);
		//Delete..
		race.addCompetitor("A", "A");
		race.addCompetitor("A", "A");
		race.addCompetitor("A", "A");
		race.addCompetitor("A", "A");
		race.addCompetitor("A", "A");
		race.addCompetitor("A", "A");
		race.addCompetitor("A", "A");
		race.addCompetitor("A", "A");
		//...
	}
	
	public void startRacePreparation() {
		
		if(race.getCompetitors().size() >= 7) {
			grid.getChildren().clear();
			grid.add(addUser, 0, 0);
			grid.add(time, 0, 1);
			GridPane.setMargin(addUser, new Insets(0, 0, 0, 50));
			GridPane.setMargin(time, new Insets(0, 0, 0, 50));
			list.getItems().clear();
		}
		else {
			
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "There must be minimum 7 competitors!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			setCss(alert);
			
			alert.showAndWait();
		}
	}
	
	public void startRace() {
		
	}
	
	public void restart() {
		
		startNewRace();
		startHorseRegistration();
	}
	
	//Add
	public void addCompetitor() {
		
		if(race.getCompetitors().size() < 10) {
			
			try {
				
				Dialog<ButtonType> dialog = new Dialog<>();
				setCss(dialog);
				dialog.setTitle(null);
				dialog.setHeaderText("Please enter the information");
				dialog.setResizable(true);
				 
				Label l1 = new Label("Rider name");
				Label l2 = new Label("Horse name");
				TextField t1 = new TextField();
				TextField t2 = new TextField();
				
				GridPane grid = new GridPane();
				grid.setHgap(4);
				grid.setVgap(10);
				grid.add(l1, 1, 1);
				grid.add(t1, 2, 1);
				grid.add(l2, 1, 2);
				grid.add(t2, 2, 2);
				dialog.getDialogPane().setContent(grid);
				         
				ButtonType cancel = new ButtonType("CANCEL", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().add(cancel);
				ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().add(ok);
				
				Optional<ButtonType> action=dialog.showAndWait();
				
				if(action.get()== ok) {
					if(!t1.getText().isEmpty() && !t2.getText().isEmpty()){
						race.addCompetitor(t1.getText(), t2.getText());
						list.getItems().add(new Label(t1.getText() + " - " + t2.getText()));
					}
					else{
						Alert alert = new Alert(AlertType.NONE, "Please check the values you entered and try again", ok);
						setCss(alert);
						alert.setTitle(null);
						alert.setHeaderText(null);
						alert.show();
					}
					
				}
				
			} 
			catch (Exception e) {
				ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Please check the values you entered and try again", ok);
				setCss(alert);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.show();
				
			}
		}
		else {
			
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "There cannot be more than 10 competitors!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			setCss(alert);
			
			alert.showAndWait();
		}
	}
	
	public void addLetterHandler(TextField tfield) {
		
		tfield.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				
				String character = event.getCharacter();
				
				try {
		    		Integer.parseInt(character);
		    	}
		    	catch(NumberFormatException e) {
		    		event.consume();
		    	}
				
		    }});
	}
	
	public void addUser() {
		
		try {
			
			Dialog<ButtonType> dialog = new Dialog<>();
			setCss(dialog);
			dialog.setTitle(null);
			dialog.setHeaderText("Please enter the information");
			dialog.setResizable(true);
			 
			Label l1 = new Label("User ID");
			Label l2 = new Label("User name");
			Label l3 = new Label("Bet competitor");
			Label l4 = new Label("Bet amout");
			TextField t1 = new TextField();
			addLetterHandler(t1);
			TextField t2 = new TextField();
			ChoiceBox<Competitor> cb3 = new ChoiceBox<Competitor>();
			cb3.getItems().addAll(race.getCompetitors().toArrayList());
			cb3.getSelectionModel().select(0);
			TextField t4 = new TextField();
			addLetterHandler(t4);
			GridPane grid = new GridPane();
			grid.setHgap(4);
			grid.setVgap(10);
			grid.add(l1, 1, 1);
			grid.add(t1, 2, 1);
			grid.add(l2, 1, 2);
			grid.add(t2, 2, 2);
			grid.add(l3, 1, 3);
			grid.add(cb3, 2, 3);
			grid.add(l4, 1, 5);
			grid.add(t4, 2, 5);
			t4.setPromptText("$");
			dialog.getDialogPane().setContent(grid);
			         
			ButtonType cancel = new ButtonType("CANCEL", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(cancel);
			ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(ok);
			
			Optional<ButtonType> action=dialog.showAndWait();
			
			if(action.get()== ok) {
				if(!t1.getText().isEmpty() && !t2.getText().isEmpty() && cb3.getValue()!=null && !t4.getText().isEmpty()){
					race.addUser(t1.getText(), t2.getText(), cb3.getValue(), Double.parseDouble(t4.getText()));
					addBet(list, cb3.getValue().toString(), Integer.parseInt(t4.getText()));
				}
				else{
					ButtonType accept = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
					Alert alert = new Alert(AlertType.NONE, "You have entered an invalid value, please check the values you entered and try again", accept);
					alert.setHeaderText(null);
					alert.setTitle(null);
					
					setCss(alert);
					
					alert.showAndWait();
				}
			}
		} 
		catch (Exception e) {
			
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "You have entered an invalid value, please check the values you entered and try again", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			setCss(alert);
			
			alert.showAndWait();
			
		}
	}
	
	public void addBet(ListView<Label> list, String name, int bet) {
		
		Label label = new Label(name + " $" + bet);
		boolean found = true;
		
		for(int i = 0; i < list.getItems().size() && found; i++) {
			
			String[] competitor = list.getItems().get(i).getText().split("-");
			String line = competitor[0] + competitor[1];
			competitor = line.split(" ");
			String competitorName = (competitor[0] + " - " + competitor[2]);
			
			if(competitorName.equals(name)) {
				
				int a = Integer.parseInt(competitor[3].substring(1));
				int newBet = a + bet;
				
				list.getItems().get(i).setText(name + " $" + newBet);
				found = false;
			}
		}
		
		if(found) {
			list.getItems().add(label);
		}
	}
	
	//View
	public void updateTime() {
		
		time.setText("Time: " + clock.getMinStr() + ":" + clock.getSecStr());
		
		if (clock.getSec() > 3) {
			loadRace();
			startRace();
		}
		
//		if (clock.getMin() >= 3) {
//			loadRace();
//			startRace();
//		}
	}
	
	public void updateRace(int rail, int pos, Image img) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		double div=(canvas.getHeight()/race.getCompetitors().size());
		gc.clearRect(0, rail*div, canvas.getWidth(), div);
	
		gc.drawImage(img, pos, rail*div, div, div);
//		gc.fillOval(pos, rail*div, div, div);
		
	}
	
	//CSS
	public void applyProperties(VBox vb) {
		
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(50, 50, 50, 50));
		vb.setSpacing(15);
	}
	
	public <T extends Dialog<?>> void setCss(T dialog) {
		
		DialogPane dialogPane = dialog.getDialogPane();
		String str = getClass().getResource("/view/view.css").toExternalForm();
		dialogPane.getStylesheets().add(getClass().getResource("/view/view.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:med/Logo.png"));
	}
}
