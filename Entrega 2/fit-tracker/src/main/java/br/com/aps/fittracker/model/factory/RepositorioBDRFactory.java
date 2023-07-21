package br.com.aps.fittracker.model.factory;

import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.exercicio.ExercicioRepository;
import br.com.aps.fittracker.model.exercicio.IExercicioRepository;
import br.com.aps.fittracker.model.programado.ExercicioProgramadoRepository;
import br.com.aps.fittracker.model.programado.IExercicioProgramadoRepository;
import br.com.aps.fittracker.model.treino.ITreinoRepositoy;
import br.com.aps.fittracker.model.treino.TreinoRepository;
import br.com.aps.fittracker.model.usuario.IUsuarioRepository;
import br.com.aps.fittracker.model.usuario.UsuarioRepository;

@Component
public class RepositorioBDRFactory implements IRepositorioFactory{
    
    private static IRepositorioFactory instance;

    private RepositorioBDRFactory() {
    }

    public static IRepositorioFactory getInstance() {
        if (instance == null) {
            RepositorioBDRFactory.instance = new RepositorioBDRFactory();
        }
        return instance;
    }
    public IUsuarioRepository criarRepositorioUsuarios() {
        return new UsuarioRepository();
    }
    public IExercicioRepository criarRepositorioExercicios() {
        return new ExercicioRepository();
    }
    public IExercicioProgramadoRepository criarRepositorioExerciciosProgramados() {
        return new ExercicioProgramadoRepository();
    }
    public ITreinoRepositoy criarRepositorioTreinos() {
        return new TreinoRepository();
    }
}
