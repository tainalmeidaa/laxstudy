// Pacote
package laxstudy.controller;

// Imports
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.beans.property.ReadOnlyStringWrapper;

// Imports - Locais
import laxstudy.database.Database;
import laxstudy.SceneManager;
import laxstudy.model.DisciplinaModel;

// Classe "DisciplinaController"
public class DisciplinaController {
	// Conexão com o Banco de Dados
    private Connection connection;
	private Database database = new Database();
	
	// Métodos - Integração com Banco de Dados
	// Cadastrar
    public void create(String nome, int nivel, String descricao) {
    	connection = database.getConnection();
    	
    	// Consulta
        String query = "INSERT INTO disciplina (nome, nivel, descricao) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nome);
            statement.setInt(2, nivel);
            statement.setString(3, descricao);
            statement.executeUpdate();
            System.out.println("\nDisciplina: o cadastro foi realizado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao cadastrar disciplina: " + e.getMessage());
        }
    }

    // Ler Todos
    public List<DisciplinaModel> readAll() {
    	connection = database.getConnection();
    	
    	// Consulta
        String query = "SELECT * FROM disciplina;";
        
        // Disciplinas Cadastradas
        List<DisciplinaModel> disciplinas = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int nivel = rs.getInt("nivel");
                String descricao = rs.getString("descricao");
                disciplinas.add(new DisciplinaModel(id, nome, nivel, descricao));
            }

            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao obter disciplinas: " + e.getMessage());
        }

        return disciplinas;
    }
    
    // Atualizar
    public void update(int id, String nome, int nivel, String descricao) {
    	connection = database.getConnection();
        
        // Consulta
        String query = "UPDATE disciplina SET nome = ?, nivel = ?, descricao = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, nome);
            statement.setInt(2, nivel);
            statement.setString(3, descricao);
            statement.setInt(4, id);
            statement.executeUpdate();
            System.out.println("\nDisciplina: o cadastro foi atualizado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao atualizar disciplina: " + e.getMessage());
        }
    }
    
    // Deletar
    public void delete(int id) {
    	connection = database.getConnection();
        
        // Consulta
        String query = "DELETE FROM disciplina WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("\nDisciplina: o cadastro foi deletado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao deletar disciplina: " + e.getMessage());
        }
    }
    
    // Métodos - Integração com FXML
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
    
    // Tabela de Disciplinas
    @FXML
    private TableView<DisciplinaModel> tabelaDisciplinas;

    @FXML
    private TableColumn<DisciplinaModel, String> colNome;

    @FXML
    private TableColumn<DisciplinaModel, String> colNivel;

    @FXML
    private TableColumn<DisciplinaModel, String> colDescricao;
    
    @FXML
    private TableColumn<DisciplinaModel, Void> colAcoes;
    
    private static final String SVG_EDIT = "M12.3 2.3l1.4 1.4-9.9 9.9H2.4v-1.4l9.9-9.9zM14.7.9l-.9-.9c-.6-.6-1.5-.6-2.1 0l-1.1 1.1 2.9 2.9 1.1-1.1c.6-.6.6-1.5.1-2z";
    private static final String SVG_TRASH = "M3 6h10l-1 10H4L3 6zm9-3v1H4V3h3.5l.5-.5h2l.5.5H12z";
    
    private SVGPath svg(String content, String color, double size) {
        SVGPath icon = new SVGPath();
        icon.setContent(content);
        icon.setStyle("-fx-fill: " + color);
        icon.setScaleX(size);
        icon.setScaleY(size);
        return icon;
    }
    
    // Métodos - Inicializável    
    @FXML
    public void initialize() {    	
        // Tabela de Disciplinas
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNivel.setCellValueFactory(cellData -> {
        	int nivel = cellData.getValue().getNivel();
            String texto;

            switch (nivel) {
            case 1: texto = "Fácil"; break;
            	case 2: texto = "Médio"; break;
                case 3: texto = "Difícil"; break;
                default: texto = "Desconhecido";
            }

            return new ReadOnlyStringWrapper(texto);
        });
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colAcoes.setCellFactory(col -> new TableCell<>() {
            private final SVGPath iconeEditar = svg(SVG_EDIT, "#4A90E2", 0.9);
            private final SVGPath iconeDeletar = svg(SVG_TRASH, "#E74C3C", 0.9);
            private final HBox box = new HBox(12, iconeEditar, iconeDeletar);

            {
                box.setStyle("-fx-alignment: center;");
                iconeEditar.setCursor(Cursor.HAND);
                iconeDeletar.setCursor(Cursor.HAND);

                iconeEditar.setOnMouseClicked(e -> {
                    try {
                        popupUpdate(getTableView().getItems().get(getIndex()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                iconeDeletar.setOnMouseClicked(e -> {
                    try {
                        popupDelete(getTableView().getItems().get(getIndex()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });        
        
        tabelaDisciplinas.getItems().setAll(readAll());
    }
    
    // Métodos - Atualizar Tabela
    public void refreshTable() {
        tabelaDisciplinas.getItems().setAll(readAll());
    }

    
    // Métodos - Pop-Up - Cadastrar
    @FXML
    private void popupCreate() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/pop-ups/CadastroDisciplina.fxml"));
        Stage stage = new Stage();
        stage.setTitle("LaxStudy - Cadastrar Disciplina");
        stage.setScene(new Scene(root));
        stage.setOnHidden(e -> refreshTable());
        stage.show();
    }
    
    // Métodos - Pop-Up - Atualizar
    @FXML
    private void popupUpdate(DisciplinaModel disc) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/pop-ups/AtualizaDisciplina.fxml"));
    	Parent root = loader.load();
    	CadastroDisciplinaController controller = loader.getController();
    	controller.setDisciplina(disc);
    	Stage stage = new Stage();
    	stage.setTitle("LaxStudy - Atualizar Disciplina");
    	stage.setScene(new Scene(root));
        stage.setOnHidden(e -> refreshTable());
    	stage.show();
    }
    
    // Métodos - Pop-Up - Deletar
    @FXML
    private void popupDelete(DisciplinaModel disc) throws Exception {
    	delete(disc.getId());
    	tabelaDisciplinas.getItems().setAll(readAll());
    }
}