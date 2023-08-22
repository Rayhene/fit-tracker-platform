package br.com.aps.fittracker.model.programado;

public interface IExercicioProgramadoRepository {
    
    public void inserir(ExercicioProgramado exercicioProgramado);
    public void atualizar(ExercicioProgramado exercicioProgramado);
    public void remover(Long id);
    public ExercicioProgramado get(Long id);
}
