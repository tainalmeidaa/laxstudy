// Pacote
package laxstudy.controller;

// Imports
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

//Imports - Locais
import laxstudy.SceneManager;

// Classe "HomeController"
public class HomeController {
	// Menu Lateral
	@FXML
    private void goHome(ActionEvent event) throws Exception {
        SceneManager.goHome(event);
    }

    @FXML
    private void goDisciplinas(ActionEvent event) throws Exception {
        SceneManager.goDisciplinas(event);
    }

    @FXML
    private void goProgresso(ActionEvent event) throws Exception {
        SceneManager.goProgresso(event);
    }

    @FXML
    private void goCronograma(ActionEvent event) throws Exception {
        SceneManager.goCronograma(event);
    }

    @FXML
    private void goPlaylist(ActionEvent event) throws Exception {
        SceneManager.goPlaylist(event);
    }
}