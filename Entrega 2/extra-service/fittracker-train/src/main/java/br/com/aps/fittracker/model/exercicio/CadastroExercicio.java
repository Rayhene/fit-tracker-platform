package br.com.aps.fittracker.model.exercicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroExercicio {

    @Autowired
    private IExercicioRepository exercicioRepository;

    public Iterable<Exercicio> getAll() {
        return exercicioRepository.getAll();
    }

    public void inserir(Exercicio exercicio) {
        exercicioRepository.inserir(exercicio);
    }
    
}
