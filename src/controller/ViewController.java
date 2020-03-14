package controller;

import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXML;
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

public class ViewController {

	Race rc = new Race(100);
	
	@FXML private AnchorPane root;
	
	@FXML private Button addComp;
	
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
		         
		ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
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
	
}
