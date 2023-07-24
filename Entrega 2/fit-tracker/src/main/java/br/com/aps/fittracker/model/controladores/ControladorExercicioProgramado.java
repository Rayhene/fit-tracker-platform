package br.com.aps.fittracker.model.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.programado.CadastroExercicioProgramado;
import br.com.aps.fittracker.model.programado.ExercicioProgramado;

@Component
public class ControladorExercicioProgramado {
    
    @Autowired
    private CadastroExercicioProgramado cadastroExercicioProgramado;

    public void inserir(ExercicioProgramado exercicioProgramado) {
        cadastroExercicioProgramado.inserir(exercicioProgramado);
    }

    public void atualizar(ExercicioProgramado exercicioProgramado) {
        cadastroExercicioProgramado.atualizar(exercicioProgramado);
    }

    public void remover(Long id) {
        cadastroExercicioProgramado.remover(id);
    }

    public ExercicioProgramado getExercicioProgramado(Long id) {
        return cadastroExercicioProgramado.getExercicioProgramado(id);
    }

}
