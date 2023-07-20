package br.com.aps.fittracker.model.treino;

public interface ITreinoRepositoy {

    void inserir(Treino treino);

    void atualizar(Treino treino);
    
    void remover(Long id);
}
