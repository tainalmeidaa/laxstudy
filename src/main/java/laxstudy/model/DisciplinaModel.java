// Pacote
package laxstudy.model;

// Classe "DisciplinaModel"
public class DisciplinaModel {
	private int id;
    private String nome;
    private int nivel;
    private String descricao;
    
    // Construtor
    public DisciplinaModel (int id, String nome, int nivel, String descricao) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.descricao = descricao;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public String getDescricao() {
        return descricao;
    }
}