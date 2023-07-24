package br.com.aps.fittracker.model.controladores;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aps.fittracker.model.exercicio.CadastroExercicio;
import br.com.aps.fittracker.model.exercicio.Exercicio;

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
