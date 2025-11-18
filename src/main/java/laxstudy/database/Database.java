// Pacote
package laxstudy.database;

// Imports
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// Classe "Database"
public class Database {
    private Connection connection;
	private static String URL = "jdbc:sqlite:src/main/resources/laxstudy.db";
    
	// Métodos
	// Criar - Conexão
	public Connection getConnection() {
	    try {
	        if (connection == null || connection.isClosed()) {
	            connection = DriverManager.getConnection(URL);
	            System.out.println("\nBanco de Dados: conexao estabelecida com sucesso!");
	        }
	    } catch (SQLException e) {
	        System.err.println("\nBanco de Dados: erro ao conectar: " + e.getMessage());
	    }
	    return connection;
	}
    
    // Criar - Tabelas
    public void createTables() {
    	// Conexão com o Banco de Dados
        getConnection();
        
        // Consulta
        String query =
        	"CREATE TABLE IF NOT EXISTS disciplina ("
			+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ "nome VARCHAR(100) NOT NULL,"
			+ "nivel INTEGER(1) NOT NULL,"
			+ "descricao VARCHAR(100) NOT NULL);"
			
			+ "CREATE TABLE IF NOT EXISTS blocoDeEstudo ("
			+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ "idDisciplina INTEGER NOT NULL,"
			+ "data DATE NOT NULL,"
			+ "horaInicial TIME NOT NULL,"
			+ "horaFinal TIME NOT NULL,"
			+ "pausado BOOLEAN NOT NULL,"
			+ "ativo BOOLEAN NOT NULL,"
			+ "concluido BOOLEAN NOT NULL,"
			+ "FOREIGN KEY (idDisciplina) REFERENCES disciplina(id));"
	
			+ "CREATE TABLE IF NOT EXISTS musica ("
			+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ "nome VARCHAR(100) NOT NULL,"
			+ "capaURL VARCHAR NOT NULL,"
			+ "arquivoURL VARCHAR NOT NULL,"
			+ "pausada BOOLEAN NOT NULL);";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("\nBanco de Dados: tabelas criadas com sucesso!");
        } catch (SQLException e) {
        	System.err.println("\nBanco de Dados: erro ao criar tabelas: " + e.getMessage());
        }
    }
    
    // Encerrar - Conexão
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("\nBanco de Dados: conexao encerrada!");
            }
        } catch (SQLException e) {
            System.err.println("\nBanco de Dados: erro ao encerrar conexao: " + e.getMessage());
        }
    }

}