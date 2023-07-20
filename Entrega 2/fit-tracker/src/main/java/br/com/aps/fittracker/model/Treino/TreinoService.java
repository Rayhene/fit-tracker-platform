package br.com.aps.fittracker.model.treino;

import org.springframework.beans.factory.annotation.Autowired;

public class TreinoService {

    @Autowired
    private ITreinoRepositoy treinoRepository;


    public void inserir(String nome) {
        Treino treino = new Treino();
        treino.setNome(nome);
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

    // public Treino consultarTreinoPeloId(Long id) {
    //     return treinoRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Treino não encontrado com o ID: " + id));
    // }
    
}
