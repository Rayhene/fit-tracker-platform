package br.com.aps.fittracker.model.treino;

import java.util.List;

import br.com.aps.fittracker.model.programado.ExercicioProgramado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Treino {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String nome;
    private String descricao;
    
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExercicioProgramado> exercicios;

    //@ManyToOne
    //@JoinColumn(name = "usuario_id")
    private Long usuarioId;
    //private Usuario usuario;

    public Treino() {
    }

    public Treino(String nome, String descricao, List<ExercicioProgramado> exercicios) {
        this.nome = nome;
        this.descricao = descricao;
        this.exercicios = exercicios;
    }

    public Treino(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Treino(Long id, String nome, String descricao, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
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

    public List<ExercicioProgramado> getExercicios() {
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

    public void setExercicios(List<ExercicioProgramado> exercicios) {
        this.exercicios = exercicios;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    //public void getUsuario(Usuario usuario) {
    //    this.usuario = usuario;
    //}
    //public void setUsuario(Usuario usuario) {
    //    this.usuario = usuario;
    //}

  
}
