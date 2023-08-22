package br.com.aps.fittracker.model.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.treino.Treino;
import br.com.aps.fittracker.model.treino.CadastroTreino;
import br.com.aps.fittracker.model.programado.ExercicioProgramado;
import br.com.aps.fittracker.model.programado.CadastroExercicioProgramado;
import br.com.aps.fittracker.model.api.IAccountAPI;

@Component
public class ControladorTreino {
    
    @Autowired
    private CadastroTreino cadastroTreino;

    @Autowired
    private CadastroExercicioProgramado cadastroExercicioProgramado;

    @Autowired
    private IAccountAPI accountService;

    // COM UTILIZAÇÃO DO CIRCUIT BREAKER
    public void inserir(Treino treino) {
        System.out.println("treino.getUsuarioId(): " + treino.getUsuarioId());
        boolean existeUsuario = accountService.existeUsuario(treino.getUsuarioId());
        System.out.println("existeUsuario " + existeUsuario);
        if (!existeUsuario) {
            throw new IllegalArgumentException("Não existe usuário cadastrado com o id informado.");
        }
        cadastroTreino.inserir(treino);
    }

    public void atualizar(Treino treino){
        cadastroTreino.atualizar(treino);
    }

    public void atualizarNomeDescricao(Treino treino) {
        cadastroTreino.atualizarNomeDescricao(treino);
    }

    public void remover(Long id){
        cadastroTreino.remover(id);
    }

    public List<Treino> listarTreinosUsuario(Long usuarioId){
        return cadastroTreino.listarTreinosUsuario(usuarioId);
    }

    public Treino buscarTreinoPeloId(Long id) {
        return cadastroTreino.buscarTreinoPeloId(id);
    }
    
    public void atualizarExercicioProgramado(ExercicioProgramado exercicioProgramado) {
        cadastroExercicioProgramado.atualizar(exercicioProgramado);
    }
}
