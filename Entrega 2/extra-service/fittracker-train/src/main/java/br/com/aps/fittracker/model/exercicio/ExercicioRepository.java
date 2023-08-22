package br.com.aps.fittracker.model.exercicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExercicioRepository implements IExercicioRepository{
    
    @Autowired
    private ExercicioDAO exercicioDAO;

    @Override
    public Iterable<Exercicio> getAll() {
        return exercicioDAO.findAll();
    }

    public void inserir(Exercicio exercicio) {
        exercicioDAO.save(exercicio);
    }
}
