package br.com.aps.fittracker.model.treino;

import java.util.List;

public interface ITreinoRepositoy {

    void inserir(Treino treino);

    void atualizar(Treino treino);

    void atualizarNomeDescricao(Treino treino);
    
    void remover(Long id);

    List<Treino> listarTreinosUsuario(Long usuarioId);

    Treino findTreinoById(Long id);
}
