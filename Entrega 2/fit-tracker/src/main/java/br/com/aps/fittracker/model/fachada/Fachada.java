package br.com.aps.fittracker.model.fachada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.controladores.ControladorTreino;
import br.com.aps.fittracker.model.treino.Treino;

@Component
public class Fachada {
    
    @Autowired
    private ControladorTreino controladorTreino;

    public void inserirTreino(String nome){
        controladorTreino.inserir(nome);
    }

    public void atualizarTreino(Treino treino){
        controladorTreino.atualizar(treino);
    }

    public void removerTreino(Long id){
        controladorTreino.remover(id);
    }
    
}
