// Pacote
package laxstudy.controller;

// Imports
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

// Classe "HomeController"
public class HomeController {
	@FXML
	private Label label1;
	
	@FXML
	private void reactToClick() {
	    label1.setText("Button clicked");
	}
}