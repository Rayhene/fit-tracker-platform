package br.com.aps.fittracker.model.treino;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroTreino {

    @Autowired
    private ITreinoRepositoy treinoRepository;


    public void inserir(Treino treino) {
        treinoRepository.inserir(treino);
    }

    public void atualizar(Treino treino) {
        treinoRepository.atualizar(treino);
    }

    public void remover(Long id) {
        treinoRepository.remover(id);
    }

    // public List<Treino> consultarTreinos() {
    //     return treinoRepository.findAll();
    // }

    public List<Treino> listarTreinosUsuario(Long usuarioId){
        return treinoRepository.listarTreinosUsuario(usuarioId);
    }

    // public Treino consultarTreinoPeloId(Long id) {
    //     return treinoRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Treino não encontrado com o ID: " + id));
    // }
    
}
