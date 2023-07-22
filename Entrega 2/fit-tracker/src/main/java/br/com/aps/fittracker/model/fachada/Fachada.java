package br.com.aps.fittracker.model.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.controladores.ControladorTreino;
import br.com.aps.fittracker.model.treino.Treino;
import br.com.aps.fittracker.model.usuario.ControladorUsuario;
import br.com.aps.fittracker.model.usuario.Usuario;

@Component
public class Fachada {
    
    @Autowired
    private ControladorTreino controladorTreino;

    @Autowired
    private ControladorUsuario controladorUsuario;

    public void inserirTreino(Treino treino){
        controladorTreino.inserir(treino);
    }

    public void atualizarTreino(Treino treino){
        controladorTreino.atualizar(treino);
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

    public boolean login(String email, String senha) {
        return controladorUsuario.login(email, senha);
    }
    
}
