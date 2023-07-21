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
import br.com.aps.fittracker.model.usuario.UsuarioRepositoryJSON;

@Component
public class RepositorioFileFactory implements IRepositorioFactory {
    
    private static IRepositorioFactory instance;

    private RepositorioFileFactory() {
    }

    public static IRepositorioFactory getInstance() {
        if (instance == null) {
            RepositorioFileFactory.instance = new RepositorioFileFactory();
        }
        return instance;
    }

    public IUsuarioRepository criarRepositorioUsuarios() {
        return new UsuarioRepositoryJSON();
    }
    public IExercicioRepository criarRepositorioExercicios() {
        return null;
    }
    public IExercicioProgramadoRepository criarRepositorioExerciciosProgramados() {
        return null;
    }
    public ITreinoRepositoy criarRepositorioTreinos() {
        return null;
    }

}
