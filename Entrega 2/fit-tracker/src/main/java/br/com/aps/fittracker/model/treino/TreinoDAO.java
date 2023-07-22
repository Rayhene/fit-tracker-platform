package br.com.aps.fittracker.model.treino;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TreinoDAO extends JpaRepository<Treino, Long>{

        List<Treino> findAllByUsuarioId(@Param("id_usuario") Long usuarioId);

    
}
