// Pacote
package laxstudy.controller;

// Imports
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

// Imports - Locais
import laxstudy.database.Database;
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
    
}