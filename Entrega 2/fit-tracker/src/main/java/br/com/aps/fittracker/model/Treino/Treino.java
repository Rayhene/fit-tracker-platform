package br.com.aps.fittracker.model.Treino;

import jakarta.persistence.*;

@Entity
public class Treino {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String nome;
    private String descricao;

    public Treino() {
    }

    public Treino(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Treino(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
