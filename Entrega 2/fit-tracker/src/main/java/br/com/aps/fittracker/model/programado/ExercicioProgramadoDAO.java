package br.com.aps.fittracker.model.programado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioProgramadoDAO extends JpaRepository<ExercicioProgramado, Long>{
    
    public Optional<ExercicioProgramado> findById(Long id);
    
}
