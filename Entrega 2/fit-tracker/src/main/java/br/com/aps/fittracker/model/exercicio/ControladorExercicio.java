package br.com.aps.fittracker.model.exercicio;

import org.springframework.beans.factory.annotation.Autowired;

public class ControladorExercicio {

    @Autowired
    private CadastroExercicio cadastroExercicio;
    
    public Iterable<Exercicio> getAll() {
        return cadastroExercicio.getAll();
    }

    public void inserir(Exercicio exercicio) {
        cadastroExercicio.inserir(exercicio);
    }
}
