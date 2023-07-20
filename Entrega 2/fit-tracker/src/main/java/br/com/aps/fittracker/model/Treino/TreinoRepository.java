package br.com.aps.fittracker.model.Treino;

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

        Optional<Treino> treinoPOptional = treinoDAO.findById(id);
        Treino treino;

        if(treinoPOptional.isPresent()){
            treino = treinoPOptional.get();
            treinoDAO.delete(treino);
        }
    }
}
