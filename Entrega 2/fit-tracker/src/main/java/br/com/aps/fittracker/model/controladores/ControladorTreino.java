package br.com.aps.fittracker.model.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.treino.Treino;
import br.com.aps.fittracker.model.treino.TreinoService;

@Component
public class ControladorTreino {
    
    @Autowired
    private TreinoService treinoService;


    public void inserir(String nome) {
        treinoService.inserir(nome);
    }

    public void atualizar(Treino treino){
        treinoService.atualizar(treino);
    }

    public void remover(Long id){
        treinoService.remover(id);
    }
}
