// Pacote
package laxstudy;

// Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Stage;

// Imports - Locais
import laxstudy.database.Database;
import laxstudy.controller.DisciplinaController;

public class App extends Application {
	private Database database = new Database();
	
	// Métodos
    // Inicializar - GUI
    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/views/home.fxml"));
        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox, 200, 100);
        stage.setTitle("LaxStudy - Disciplinas");
        stage.setScene(scene);
        stage.show();

        // Conexão com o Banco de Dados
        database.getConnection();
    }
    
    // Inicializar - JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}