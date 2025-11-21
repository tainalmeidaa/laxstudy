// Pacote
package laxstudy;

// Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Classe "SceneManager"
public class SceneManager {
	public static void switchTo(String fxml, ActionEvent event, String title) throws Exception {
        Parent root = FXMLLoader.load(SceneManager.class.getResource("/views/" + fxml));

        Scene scene = new Scene(root);

        String css = SceneManager.class.getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void goHome(ActionEvent e) throws Exception {
        switchTo("Home.fxml", e, "LaxStudy - Home");
    }

    public static void goDisciplinas(ActionEvent e) throws Exception {
        switchTo("Disciplinas.fxml", e, "LaxStudy - Disciplinas");
    }

    public static void goProgresso(ActionEvent e) throws Exception {
        switchTo("Progresso.fxml", e, "LaxStudy - Progresso");
    }

    public static void goCronograma(ActionEvent e) throws Exception {
        switchTo("Cronograma.fxml", e, "LaxStudy - Cronograma");
    }

    public static void goPlaylist(ActionEvent e) throws Exception {
        switchTo("Playlist.fxml", e, "LaxStudy - Playlist");
    }
}