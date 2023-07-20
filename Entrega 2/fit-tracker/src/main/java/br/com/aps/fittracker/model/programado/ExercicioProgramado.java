package br.com.aps.fittracker.model.programado;

import br.com.aps.fittracker.model.exercicio.Exercicio;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ExercicioProgramado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int series;
    private int repeticoes;
    private double carga;
    private int descanso;

    public ExercicioProgramado() {}
    public ExercicioProgramado(Long id, int series, int repeticoes, double carga, int descanso) {
        this.id = id;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.descanso = descanso;
    }
    public ExercicioProgramado(int series, int repeticoes, double carga, int descanso) {
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.descanso = descanso;
    }

    //getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }
    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }
    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public double getCarga() {
        return carga;
    }
    public void setCarga(double carga) {
        this.carga = carga;
    }

    public int getDescanso() {
        return descanso;
    }
    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }

    
}
