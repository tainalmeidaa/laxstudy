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
import laxstudy.model.MusicaModel;

// Classe "MusicaController"
public class MusicaController {
	// Conexão com o Banco de Dados
    private Connection connection;
	private Database database = new Database();
	
	// Métodos - Integração com Banco de Dados
	// Cadastrar
    public void create(String nome, String capaURL, String arquivoURL, boolean pausada) {
    	connection = database.getConnection();
    	
    	// Consulta
        String query = "INSERT INTO musica (nome, capaURL, arquivoURL, pausada) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nome);
            statement.setString(2, capaURL);
            statement.setString(3, arquivoURL);
            statement.setBoolean(4, pausada);
            statement.executeUpdate();
            System.out.println("\nMusica: o cadastro foi realizado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao cadastrar musica: " + e.getMessage());
        }
    }

    // Ler Todos
    public List<MusicaModel> readAll() {
    	connection = database.getConnection();
    	
    	// Consulta
        String query = "SELECT * FROM musica;";
        
        // Disciplinas Cadastradas
        List<MusicaModel> musicas = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String capaURL = rs.getString("capaURL");
                String arquivoURL = rs.getString("arquivoURL");
                boolean pausada = rs.getBoolean("pausada");
                musicas.add(new MusicaModel(id, nome, capaURL, arquivoURL, pausada));
            }

            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao obter musicas: " + e.getMessage());
        }

        return musicas;
    }
    
    // Atualizar
    public void update(int id, String nome, String capaURL, String arquivoURL, boolean pausada) {
    	connection = database.getConnection();
        
        // Consulta
        String query = "UPDATE musica SET nome = ?, capaURL = ?, arquivoURL = ?, pausada = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, nome);
            statement.setString(2, capaURL);
            statement.setString(3, arquivoURL);
            statement.setBoolean(4, pausada);
            statement.setInt(5, id);
            statement.executeUpdate();
            System.out.println("\nMusica: o cadastro foi atualizado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao atualizar musica: " + e.getMessage());
        }
    }
    
    // Deletar
    public void delete(int id) {
    	connection = database.getConnection();
        
        // Consulta
        String query = "DELETE FROM musica WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("\nMusica: o cadastro foi deletado com sucesso!");
            database.closeConnection();
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao deletar musica: " + e.getMessage());
        }
    }
    
    // Métodos - Integração com FXML
    
}