package br.com.aps.fittracker.model.programado;

public interface IRepositorioExercicioProgramado {
    
    public void inserir(ExercicioProgramado exercicioProgramado);
    public void atualizar(ExercicioProgramado exercicioProgramado);
    public void remover(Long id);
    public ExercicioProgramado buscarPorId(Long id);
}
