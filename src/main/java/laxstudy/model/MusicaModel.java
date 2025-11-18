// Pacote
package laxstudy.model;

// Classe "MusicaModel"
public class MusicaModel {
	private int id;
    private String nome;
    private String capaURL;
    private String arquivoURL;
    private boolean pausada;
    
    // Construtor
    public MusicaModel (int id, String nome, String capaURL, String arquivoURL, boolean pausada) {
        this.id = id;
        this.nome = nome;
        this.capaURL = capaURL;
        this.arquivoURL = arquivoURL;
        this.pausada = pausada;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCapaURL() {
        return capaURL;
    }
    
    public String getArquivoURL() {
        return arquivoURL;
    }
    
    public boolean getPausada() {
        return pausada;
    }
}