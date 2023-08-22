package br.com.aps.fittracker.model.programado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroExercicioProgramado {
    
    @Autowired
    private IExercicioProgramadoRepository exercicioProgramadoRepository;

    public void inserir(ExercicioProgramado exercicioProgramado) {
        exercicioProgramadoRepository.inserir(exercicioProgramado);
    }

    public void atualizar(ExercicioProgramado exercicioProgramado) {
        exercicioProgramadoRepository.atualizar(exercicioProgramado);
    }

    public void remover(Long id) {
        exercicioProgramadoRepository.remover(id);
    }

    public ExercicioProgramado getExercicioProgramado(Long id) {
        return exercicioProgramadoRepository.get(id);
    }


}
