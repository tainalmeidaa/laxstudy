// Pacote
package laxstudy.model;

// Imports
import java.time.LocalDate;
import java.time.LocalTime;

// Classe "BlocoDeEstudo"
public class BlocoDeEstudoModel {
	private int id;
	private int idDisciplina;
	private LocalDate data;
	private LocalTime horaInicial;
	private LocalTime horaFinal;
	private boolean pausado;
	private boolean ativo;
	private boolean concluido;
	
    // Construtor
    public BlocoDeEstudoModel (int id, int idDisciplina, LocalDate data, LocalTime horaInicial, LocalTime horaFinal, boolean pausado, boolean ativo, boolean concluido) {
        this.id = id;
        this.idDisciplina = idDisciplina;
        this.data = data;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.pausado = pausado;
        this.ativo = ativo;
        this.concluido = concluido;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public int getIdDisciplina() {
        return idDisciplina;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public LocalTime getHoraInicial() {
        return horaInicial;
    }
    
    public LocalTime getHoraFinal() {
        return horaFinal;
    }
    
    public boolean getPausado() {
        return pausado;
    }
    
    public boolean getAtivo() {
        return ativo;
    }
    
    public boolean getConcluido() {
        return concluido;
    }
}