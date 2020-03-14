package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Race;

public class ViewController implements Initializable {

	Race rc;
	
	@FXML private AnchorPane root;
	
	@FXML private AnchorPane ap1;
	@FXML private AnchorPane ap2;
	
	@FXML private Button addComp;
	@FXML private Button addUser;
	@FXML private Button startRace;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startNewRace();
		loadHorseRegistration();
		loadRacePreparation();
		startHorseRegistration();
	}
	
	public void startNewRace() {
		rc = null;
		rc = new Race(100);
	}
	
	public void loadHorseRegistration() {

		ap1 = new AnchorPane();
		addComp = new Button("Add competitor");
		addComp.setTranslateX(100);
		addComp.setTranslateY(100);
		addComp.setOnAction(e -> {
			addCompetitor();
		});
		
		startRace = new Button("Start race");
		startRace.setTranslateX(100);
		startRace.setTranslateY(150);
		startRace.setOnAction(e -> {
			startRacePreparation();
		});
		
		ap1.getChildren().addAll(addComp, startRace);
		
	}
	
	public void startHorseRegistration() {
		
		root.getChildren().clear();
		root.getChildren().addAll(ap1);
		
	}
	
	public void loadRacePreparation() {
		
		ap2 = new AnchorPane();
		addUser = new Button("Add user");
		addUser.setTranslateX(100);
		addUser.setTranslateY(100);
		addUser.setOnAction(e -> {
			addUser();
		});
		ap2.getChildren().addAll(addUser);
	}
	
	public void startRacePreparation() {
		
		root.getChildren().clear();
		root.getChildren().addAll(ap2);
		
	}
	
	public void addCompetitor() {

		Dialog<ArrayList<String>> dialog = new Dialog<>();
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
			alert.setTitle("ERROR");
			alert.setHeaderText("You have entered an invalid value");
			alert.setContentText("Please check the values you entered and try again");
			alert.showAndWait();
			
		}
		

	}
}
