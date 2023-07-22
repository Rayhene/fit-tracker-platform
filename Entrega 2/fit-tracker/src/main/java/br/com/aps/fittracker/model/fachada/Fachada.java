package br.com.aps.fittracker.model.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.controladores.ControladorTreino;
import br.com.aps.fittracker.model.treino.Treino;

@Component
public class Fachada {
    
    @Autowired
    private ControladorTreino controladorTreino;

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
    
}
