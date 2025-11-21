// Pacote
package laxstudy.controller;

// Imports
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

// Imports - Locais
import laxstudy.model.DisciplinaModel;

// Classe "CadastroDisciplinaController"
public class CadastroDisciplinaController {	
    private DisciplinaModel disciplina;
	
    @FXML
    private TextField campoNome;
    
    @FXML
    private TextArea campoDescricao;
    
    @FXML
    private ComboBox<String> campoNivel;
    
    // Métodos - Inicializável
    @FXML
    public void initialize() {
        campoNivel.getItems().addAll("Fácil", "Médio", "Difícil");
    }
    
    // Métodos - Pop-Up - Cadastrar
    @FXML
    private void goCreate() {
        String nome = campoNome.getText();
        int nivel = switch (campoNivel.getValue()) {
            case "Fácil" -> 1;
            case "Médio" -> 2;
            case "Difícil" -> 3;
            default -> 0;
        };
        String descricao = campoDescricao.getText();

        new DisciplinaController().create(nome, nivel, descricao);
        
        Stage stage = (Stage) campoNome.getScene().getWindow();
        stage.close();
    }
    
    // Métodos - Pop-Up - Atualizar
    public void setDisciplina(DisciplinaModel disciplina) {
        this.disciplina = disciplina;

        campoNome.setText(disciplina.getNome());
        campoDescricao.setText(disciplina.getDescricao());

        switch (disciplina.getNivel()) {
            case 1 -> campoNivel.setValue("Fácil");
            case 2 -> campoNivel.setValue("Médio");
            case 3 -> campoNivel.setValue("Difícil");
        }
    }
    
    @FXML
    private void goUpdate() {
        String nome = campoNome.getText();
        int nivel = switch (campoNivel.getValue()) {
            case "Fácil" -> 1;
            case "Médio" -> 2;
            case "Difícil" -> 3;
            default -> 0;
        };
        String descricao = campoDescricao.getText();

        new DisciplinaController().update(disciplina.getId(), nome, nivel, descricao);

        Stage stage = (Stage) campoNome.getScene().getWindow();
        stage.close();
    }
}