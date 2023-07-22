package br.com.aps.fittracker.model.treino;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TreinoRepository implements ITreinoRepositoy{

    @Autowired
    private TreinoDAO treinoDAO;

    @Override
    public void inserir(Treino treino){
        treinoDAO.save(treino);
    }
    
    @Override
    public void atualizar(Treino treino){
        treinoDAO.save(treino);
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
}
