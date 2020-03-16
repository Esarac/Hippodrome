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
import javafx.scene.Scene;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import model.Clock;
import model.Competitor;
import model.Race;
import thread.ClockThread;

public class ViewController implements Initializable {

	private Race race;
	private Clock clock;
	private VBox vb2;
	private VBox vb3;
	private Button addUser;
	private Button newRace;
	private Label time;
	private Label l2;
	
	@FXML private BorderPane root;
	@FXML private Button addComp; 
	@FXML private Button startRace;
	@FXML private GridPane grid;
	@FXML private Pane image;
	@FXML private ListView<Label> list;
	
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
		
		vb2 = new VBox();
		applyProperties(vb2);
		addUser = new Button("Add user");
		addUser.setOnAction(e -> {
			addUser();
		});
		
		time = new Label();
		
		clock = null;
		clock = new Clock();
		
		new ClockThread(clock, this).start();
		
		vb2.getChildren().addAll(addUser, time);
	}
	
	public void loadRace() {
		
		vb3 = new VBox();
		applyProperties(vb3);
		newRace = new Button("Start new race");
		newRace.setOnAction(e -> {
			restart();
		});
		
		vb3.getChildren().addAll(newRace);
		
		
	}
		
	//Start
	public void startHorseRegistration() {
		if(!root.getChildren().contains(grid)) {
			root.getChildren().clear();
			grid.getChildren().clear();
			grid.add(addComp, 0 , 0);
			grid.add(startRace, 0 , 1);
			image.setId("track");
			root.setLeft(grid);
			root.setCenter(image);
		}
	}
	
	public void startNewRace() {
		race = null;
		race = new Race(100);
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
		root.setLeft(null);
		root.setCenter(vb3);
		
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
				dialog.setTitle("Hello");
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
			Alert alert = new Alert(AlertType.NONE, "There cannot be more that 10 competitors!", ok);
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
			dialog.setTitle("Hello");
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
			t4.setPromptText("US$");
			dialog.getDialogPane().setContent(grid);
			         
			ButtonType cancel = new ButtonType("CANCEL", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(cancel);
			ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(ok);
			
			Optional<ButtonType> action=dialog.showAndWait();
			
			if(action.get()== ok) {
				if(!t1.getText().isEmpty() && !t2.getText().isEmpty() && cb3.getValue()!=null && !t4.getText().isEmpty()){
					race.addUser(t1.getText(), t2.getText(), cb3.getValue(), Double.parseDouble(t4.getText()));
					list.getItems().add(new Label(t1.getText() + " - " + t2.getText() + " - " + cb3.getValue() + " - " + t4.getText()));
				}
				else{
					Alert alert = new Alert(AlertType.ERROR);
					setCss(alert);
					alert.setTitle("ERROR");
					alert.setHeaderText("You have entered an invalid value");
					alert.setContentText("Please check the values you entered and try again");
					alert.showAndWait();
				}
			}
		} 
		catch (Exception e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			setCss(alert);
			alert.setTitle("ERROR");
			alert.setHeaderText("You have entered an invalid value");
			alert.setContentText("Please check the values you entered and try again");
			alert.showAndWait();
			
		}
	}
	
	//View
	public void updateTime() {
		
		time.setText("Time: " + clock.getMinStr() + ":" + clock.getSecStr());
		
//		if (clock.getSec() > 3) {
//			loadRace();
//			startRace();
//		}
		
		if (clock.getMin() >= 3) {
			loadRace();
			startRace();
		}
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
