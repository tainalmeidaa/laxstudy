// Pacote
package laxstudy.controller;

// Imports
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

// Imports - Locais
import laxstudy.database.Database;
import laxstudy.SceneManager;
import laxstudy.model.BlocoDeEstudoModel;

// Classe "BlocoDeEstudoController"
public class BlocoDeEstudoController {
	// Conexão com o Banco de Dados
    private Connection connection;
	private Database database = new Database();
	
	// Métodos - Integração com Banco de Dados
	// Cadastrar
    public void create(int idDisciplina, LocalDate data, LocalTime horaInicial, LocalTime horaFinal, boolean pausado, boolean ativo, boolean concluido) {
    	connection = database.getConnection();
    	
    	// Consulta
        String query = "INSERT INTO blocoDeEstudo (idDisciplina, data, horaInicial, horaFinal, pausado, ativo, concluido) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDisciplina);
            statement.setDate(2, java.sql.Date.valueOf(data));
            statement.setTime(3, java.sql.Time.valueOf(horaInicial));
            statement.setTime(4, java.sql.Time.valueOf(horaFinal));
            statement.setBoolean(5, pausado);
            statement.setBoolean(6, ativo);
            statement.setBoolean(7, concluido);
            statement.executeUpdate();
            System.out.println("\nBlocoDeEstudo: o cadastro foi realizado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao cadastrar bloco de estudo: " + e.getMessage());
        }
    }

    // Ler Todos
    public List<BlocoDeEstudoModel> readAll() {
    	connection = database.getConnection();
    	
    	// Consulta
        String query = "SELECT * FROM blocoDeEstudo;";
        
        // BlocoDeEstudo Cadastradas
        List<BlocoDeEstudoModel> blocosDeEstudo = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                int idDisciplina = rs.getInt("idDisciplina");
                LocalDate data = rs.getDate("data").toLocalDate();
                LocalTime horaInicial = rs.getTime("horaInicial").toLocalTime();
                LocalTime horaFinal = rs.getTime("horaFinal").toLocalTime();
                boolean pausado = rs.getBoolean("pausado");
                boolean ativo = rs.getBoolean("ativo");
                boolean concluido = rs.getBoolean("concluido");
                blocosDeEstudo.add(new BlocoDeEstudoModel(id, idDisciplina, data, horaInicial, horaFinal, pausado, ativo, concluido));
            }

            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao obter blocos de estudo: " + e.getMessage());
        }

        return blocosDeEstudo;
    }
    
    // Atualizar
    public void update(int id, LocalDate data, LocalTime horaInicial, LocalTime horaFinal, boolean pausado, boolean ativo, boolean concluido) {
    	connection = database.getConnection();
        
        // Consulta
        String query = "UPDATE blocoDeEstudo SET data = ?, horaInicial = ?, horaFinal = ?, pausado = ?, ativo = ?, concluido = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setDate(1, java.sql.Date.valueOf(data));
        	statement.setTime(2, java.sql.Time.valueOf(horaInicial));
        	statement.setTime(3, java.sql.Time.valueOf(horaFinal));
            statement.setBoolean(4, pausado);
            statement.setBoolean(5, ativo);
            statement.setBoolean(6, concluido);
            statement.setInt(7, id);
            statement.executeUpdate();
            System.out.println("\nBlocoDeEstudo: o cadastro foi atualizado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao atualizar bloco de estudo: " + e.getMessage());
        }
    }
    
    // Deletar
    public void delete(int id) {
    	connection = database.getConnection();
        
        // Consulta
        String query = "DELETE FROM blocoDeEstudo WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("\nBlocoDeEstudo: o cadastro foi deletado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao deletar bloco de estudo: " + e.getMessage());
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
}