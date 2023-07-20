package br.com.aps.fittracker.model.Treino;

public interface ITreinoRepositoy {

    void inserir(Treino treino);

    void atualizar(Treino treino);
    
    void remover(Long id);
}
