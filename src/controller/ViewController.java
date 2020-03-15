package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Clock;
import model.Race;
import thread.ClockThread;

public class ViewController implements Initializable {

	private Race rc;
	private Clock clock;
	private VBox ap2;
	private Button addUser;
	private Label l1;
	private Label l2;
	
	@FXML private BorderPane root;
	@FXML private VBox ap1;	
	@FXML private Button addComp; 
	@FXML private Button startRace;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startNewRace();
		loadHorseRegistration();
		startHorseRegistration();
	}
	
	public void startNewRace() {
		rc = new Race(100);
	}
	
	public void applyProperties(VBox vb) {
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(50, 50, 50, 50));
		vb.setSpacing(15);
	}
	
	public <T extends Dialog<?>> void setCss(T dialog) {
		
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/view.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:med/Logo.png"));
	}
	
	public void loadHorseRegistration() {

		addComp.setOnAction(e -> {
			addCompetitor();
		});
		
		startRace.setOnAction(e -> {
			loadRacePreparation();
			startRacePreparation();
		});
	}
	
	public void startHorseRegistration() {
		root.setCenter(ap1);
	}
	
	public void loadRacePreparation() {
		
		ap2 = new VBox();
		applyProperties(ap2);
		addUser = new Button("Add user");
		addUser.setOnAction(e -> {
			addUser();
		});
		
		l1 = new Label();
		
		clock = null;
		clock = new Clock();
		
		new ClockThread(clock, this).start();
		
		ap2.getChildren().addAll(addUser, l1);
	}
	
	public void updateTime() {
		
		l1.setText("Time: " + clock.getMinStr() + ":" + clock.getSecStr());
		
		if (clock.getMin() >= 3) {
			startRace();
		}
	}
	
	public void startRacePreparation() {
		root.setCenter(ap2);	
	}
	
	public void startRace() {
		
	}
	
	public void addCompetitor() {

		Dialog<ArrayList<String>> dialog = new Dialog<>();
		setCss(dialog);
		dialog.setTitle("Hello");
		dialog.setHeaderText("Please enter the information");
		dialog.setResizable(true);
		 
		Label l1 = new Label("Horse name: ");
		Label l2 = new Label("Rider name: ");
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		         
		GridPane grid = new GridPane();
		grid.add(l1, 1, 1);
		grid.add(t1, 2, 1);
		grid.add(l2, 1, 2);
		grid.add(t2, 2, 2);
		dialog.getDialogPane().setContent(grid);
		         
		ButtonType buttonTypeOk = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		
		dialog.setResultConverter(dialogButton -> {
			
			if (dialogButton == buttonTypeOk) {
	            ArrayList<String> res = new ArrayList<>();
	            res.add(t1.getText());
	            res.add(t2.getText());
	            return res;
			}
			
			return null;
		});
		         
		Optional<ArrayList<String>> result = dialog.showAndWait();
		
		result.ifPresent(alist -> rc.addCompetitor(alist.get(0), alist.get(1)));
			
	}
	
	public void addUser() {
		
		try {
			
			Dialog<ArrayList<String>> dialog = new Dialog<>();
			setCss(dialog);
			dialog.setTitle("Hello");
			dialog.setHeaderText("Please enter the information");
			dialog.setResizable(true);
			 
			Label l1 = new Label("User ID: ");
			Label l2 = new Label("User name: ");
			Label l3 = new Label("Bet horse name: ");
			Label l4 = new Label("Bet rider name: ");
			Label l5 = new Label("Bet amout: ");
			TextField t1 = new TextField();
			TextField t2 = new TextField();
			TextField t3 = new TextField();
			TextField t4 = new TextField();
			TextField t5 = new TextField();
			         
			GridPane grid = new GridPane();
			grid.add(l1, 1, 1);
			grid.add(t1, 2, 1);
			grid.add(l2, 1, 2);
			grid.add(t2, 2, 2);
			grid.add(l3, 1, 3);
			grid.add(t3, 2, 3);
			grid.add(l4, 1, 4);
			grid.add(t4, 2, 4);
			grid.add(l5, 1, 5);
			grid.add(t5, 2, 5);
			dialog.getDialogPane().setContent(grid);
			         
			ButtonType buttonTypeOk = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
			
			dialog.setResultConverter(dialogButton -> {
				
				if (dialogButton == buttonTypeOk) {
		            ArrayList<String> res = new ArrayList<>();
		            res.add(t1.getText());
		            res.add(t2.getText());
		            res.add(t3.getText());
		            res.add(t4.getText());
		            res.add(t5.getText());      
		            return res;
				}
				
				return null;
			});
			         
			Optional<ArrayList<String>> result = dialog.showAndWait();
			
			result.ifPresent(alist -> rc.addUser(alist.get(0), alist.get(1), alist.get(2), alist.get(3), Double.parseDouble(alist.get(4))));
			
			
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
}
