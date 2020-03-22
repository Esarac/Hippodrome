package view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/view.css");
			root.getStyleClass().add("pane");
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("file:med/Logo.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMinWidth(1450);
			primaryStage.setMinHeight(800);
			primaryStage.centerOnScreen();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}