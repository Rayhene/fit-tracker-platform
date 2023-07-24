package br.com.aps.fittracker.model.treino;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.programado.ExercicioProgramado;

@Component
public class TreinoRepository implements ITreinoRepositoy{

    @Autowired
    private TreinoDAO treinoDAO;

    @Override
    public void inserir(Treino treino){
        List<ExercicioProgramado> exercicios = treino.getExercicios();
        Treino treinoSalvo;

        if (exercicios != null) {
            treino.setExercicios(null);
            treinoSalvo = treinoDAO.save(treino);
            for (ExercicioProgramado exercicio : exercicios) {
                exercicio.setTreino(treinoSalvo);
            }
            treinoSalvo.setExercicios(exercicios);
            atualizar(treinoSalvo);
        } else {
            treinoDAO.save(treino);
        }
    }
    
    @Override
    public void atualizar(Treino treino){
        // Verifica se o ID do treino é nulo ou não existe no banco de dados
        if (treino.getId() == null || !treinoDAO.existsById(treino.getId())) {
            throw new IllegalArgumentException("Treino não cadastrado.");
        }

        treinoDAO.save(treino);
    }

    @Override
    public void atualizarNomeDescricao(Treino treino){
        Optional<Treino> treinoOptional = treinoDAO.findById(treino.getId());
        if(treinoOptional.isPresent()){
            Treino treinoRetrieved = treinoOptional.get();
            treinoRetrieved.setDescricao(treino.getDescricao());
            treinoRetrieved.setNome(treino.getNome());
            treinoDAO.save(treinoRetrieved);
        }
    }

    @Override
    public void remover(Long id){

        Optional<Treino> treinoOptional = treinoDAO.findById(id);
        Treino treino;

        if(treinoOptional.isPresent()){
            treino = treinoOptional.get();
            treinoDAO.delete(treino);
        }
    }

    @Override
    public List<Treino> listarTreinosUsuario(Long usuarioId){
        return treinoDAO.findAllByUsuarioId(usuarioId);
    }

    public Treino findTreinoById(Long id){
        Optional<Treino> treinoOptional = treinoDAO.findById(id);
        Treino treino = null;

        if(treinoOptional.isPresent()){
            treino = treinoOptional.get();
        }

        return treino;
    }
}
