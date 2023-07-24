package br.com.aps.fittracker.model.programado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExercicioProgramadoRepository implements IExercicioProgramadoRepository {
    
    @Autowired
    private ExercicioProgramadoDAO exercicioProgramadoDAO;

    @Override
    public void inserir(ExercicioProgramado exercicioProgramado) {
        exercicioProgramadoDAO.save(exercicioProgramado);
    }

    @Override
    public void atualizar(ExercicioProgramado exercicioProgramado) {
        if(exercicioProgramado.getId() == null || !exercicioProgramadoDAO.existsById(exercicioProgramado.getId())){
            throw new IllegalArgumentException("Exercício programado não localizado.");
        }
        ExercicioProgramado exercicioProgramadoFetched = get(exercicioProgramado.getId());
        exercicioProgramado.setTreino(exercicioProgramadoFetched.getTreino());
        exercicioProgramadoDAO.save(exercicioProgramado);
    }

    @Override
    public void remover(Long id) {
        exercicioProgramadoDAO.deleteById(id);
    }

    @Override
    //Retornar null ou lançar exceção?
    public ExercicioProgramado get(Long id) {
        ExercicioProgramado exercicioProgramado = null;
        Optional<ExercicioProgramado> option = exercicioProgramadoDAO.findById(id);
        if(option.isPresent()){
            exercicioProgramado = option.get();
        }
        return exercicioProgramado;
    }
}
