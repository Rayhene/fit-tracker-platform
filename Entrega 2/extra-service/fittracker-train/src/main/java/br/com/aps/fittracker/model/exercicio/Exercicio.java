package br.com.aps.fittracker.model.exercicio;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exercicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String musculo;



    public Exercicio() {}
    public Exercicio(Long id, String nome, String descricao, String musculo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.musculo = musculo;
    }
    public Exercicio(String nome, String descricao, String musculo) {
        this.nome = nome;
        this.descricao = descricao;
        this.musculo = musculo;
    }

    
    //getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMusculo() {
        return musculo;
    }
    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao; 
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
