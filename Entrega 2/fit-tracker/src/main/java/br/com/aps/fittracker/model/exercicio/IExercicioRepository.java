package br.com.aps.fittracker.model.exercicio;

public interface IExercicioRepository {
        
    public Iterable<Exercicio> getAll();
    public void inserir(Exercicio exercicio);
    
}
