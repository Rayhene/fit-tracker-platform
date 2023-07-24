package br.com.aps.fittracker.model.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.controladores.ControladorExercicioProgramado;
import br.com.aps.fittracker.model.controladores.ControladorTreino;
import br.com.aps.fittracker.model.controladores.ControladorUsuario;
import br.com.aps.fittracker.model.programado.ExercicioProgramado;
import br.com.aps.fittracker.model.treino.Treino;
import br.com.aps.fittracker.model.usuario.Usuario;

@Component
public class Fachada {
    
    @Autowired
    private ControladorTreino controladorTreino;

    @Autowired
    private ControladorUsuario controladorUsuario;

    @Autowired
    private ControladorExercicioProgramado controladorExercicioProgramado;

    public void inserirTreino(Treino treino){
        controladorTreino.inserir(treino);
    }

    public void atualizarTreino(Treino treino){
        controladorTreino.atualizar(treino);
    }

    public void atualizarTreinoNomeDescricao(Treino treino){
        controladorTreino.atualizarNomeDescricao(treino);
    }

    public void removerTreino(Long id){
        controladorTreino.remover(id);
    }

    public List<Treino> listarTreinosUsuario(Long usuarioId){
        return controladorTreino.listarTreinosUsuario(usuarioId);
    }

    public void inserirUsuario(Usuario usuario) {
        controladorUsuario.inserir(usuario);
    }

    public Usuario login(String email, String senha) {
        return controladorUsuario.login(email, senha);
    }

    public Usuario loginGoogle(String idToken) {
        return controladorUsuario.loginGoogle(idToken);
    }

    public Treino buscarTreinoPeloId(Long idTreino) {
        return controladorTreino.buscarTreinoPeloId(idTreino);
    }

    public void atualizarExercicioProgramado(ExercicioProgramado exercicio) {
        controladorExercicioProgramado.atualizar(exercicio);
    }
    
}
