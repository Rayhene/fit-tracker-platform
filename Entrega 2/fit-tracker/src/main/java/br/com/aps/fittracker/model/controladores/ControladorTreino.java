package br.com.aps.fittracker.model.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.treino.Treino;
import br.com.aps.fittracker.model.treino.CadastroTreino;

@Component
public class ControladorTreino {
    
    @Autowired
    private CadastroTreino cadastroTreino;


    public void inserir(Treino treino) {
        cadastroTreino.inserir(treino);
    }

    public void atualizar(Treino treino){
        cadastroTreino.atualizar(treino);
    }

    public void remover(Long id){
        cadastroTreino.remover(id);
    }

    public List<Treino> listarTreinosUsuario(Long usuarioId){
        return cadastroTreino.listarTreinosUsuario(usuarioId);
    }
}
