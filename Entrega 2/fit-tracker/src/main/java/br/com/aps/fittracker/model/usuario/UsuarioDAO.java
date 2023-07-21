package br.com.aps.fittracker.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
    
    public Usuario findByEmail(String email);
    
}
