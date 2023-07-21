package br.com.aps.fittracker.model.factory;

import br.com.aps.fittracker.model.exercicio.IExercicioRepository;
import br.com.aps.fittracker.model.programado.IExercicioProgramadoRepository;
import br.com.aps.fittracker.model.treino.ITreinoRepositoy;
import br.com.aps.fittracker.model.usuario.IUsuarioRepository;

public interface IRepositorioFactory {

    public IUsuarioRepository criarRepositorioUsuarios();
    public IExercicioRepository criarRepositorioExercicios();
    public IExercicioProgramadoRepository criarRepositorioExerciciosProgramados();
    public ITreinoRepositoy criarRepositorioTreinos();
    
}
