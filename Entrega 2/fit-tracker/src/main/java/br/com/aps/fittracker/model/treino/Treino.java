package br.com.aps.fittracker.model.treino;

import java.util.List;

import br.com.aps.fittracker.model.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Treino {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String nome;
    private String descricao;
    private List<String> exercicios;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Treino() {
        System.out.println("Treino criado");
    }

    public Treino(String nome, String descricao, List<String> exercicios) {
        this.nome = nome;
        this.descricao = descricao;
        this.exercicios = exercicios;
    }

    public Treino(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Treino(Long id, String nome, String descricao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.usuario = usuario;
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

    public List<String> getExercicios() {
        return exercicios;
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

    public void setExercicios(List<String> exercicios) {
        this.exercicios = exercicios;
    }

    public void getUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

  
}
