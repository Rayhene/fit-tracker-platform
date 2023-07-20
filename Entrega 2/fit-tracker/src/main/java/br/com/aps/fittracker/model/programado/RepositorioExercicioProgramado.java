package br.com.aps.fittracker.model.programado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aps.fittracker.model.treino.Treino;

public class RepositorioExercicioProgramado implements IRepositorioExercicioProgramado {
    
    @Autowired
    private ExercicioProgramadoDAO exercicioProgramadoDAO;

    @Override
    public void inserir(ExercicioProgramado exercicioProgramado) {
        exercicioProgramadoDAO.save(exercicioProgramado);
    }

    @Override
    public void atualizar(ExercicioProgramado exercicioProgramado) {
        exercicioProgramadoDAO.save(exercicioProgramado);
    }

    @Override
    public void remover(Long id) {
        exercicioProgramadoDAO.deleteById(id);
    }

    @Override
    //Retornar null ou lançar exceção?
    public ExercicioProgramado buscarPorId(Long id) {
        ExercicioProgramado exercicioProgramado = null;
        Optional<ExercicioProgramado> option = exercicioProgramadoDAO.findById(id);
        if(option.isPresent()){
            exercicioProgramado = option.get();
        }
        return exercicioProgramado;
    }
}
