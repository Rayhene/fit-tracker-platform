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

    public void atualizarNomeDescricao(Treino treino) {
        treinoRepository.atualizarNomeDescricao(treino);
    }

    public void remover(Long id) {
        treinoRepository.remover(id);
    }

    public List<Treino> listarTreinosUsuario(Long usuarioId){
        return treinoRepository.listarTreinosUsuario(usuarioId);
    }

    public Treino buscarTreinoPeloId(Long id) {
        return treinoRepository.findTreinoById(id);
    }
    
}
