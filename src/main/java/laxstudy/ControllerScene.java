package laxstudy;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ControllerScene {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToSceneHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("LaxStudy - Home");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToSceneDisciplina(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/views/Disciplinas.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("LaxStudy - Disciplinas");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}


}
