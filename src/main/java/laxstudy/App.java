// Pacote
package laxstudy;

// Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import javafx.stage.Stage;

// Imports - Locais
import laxstudy.database.Database;

// Classe "App"
public class App extends Application {
	private Database database = new Database();
	
	// Métodos
    // Inicializar - GUI
    @Override
    public void start(Stage stage) throws Exception {
    	Font.loadFont(getClass().getResource("/fonts/Poppins.ttf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("/fonts/Inter.ttf").toExternalForm(), 10);
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
        
        Scene scene = new Scene(root);
        
        // Conexão com o CSS
        String css = getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        stage.setTitle("LaxStudy - Home");
        stage.setScene(scene);
        stage.show();

        // Conexão com o Banco de Dados
        database.getConnection();
        database.createTables();
    }
    
    // Inicializar - JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}