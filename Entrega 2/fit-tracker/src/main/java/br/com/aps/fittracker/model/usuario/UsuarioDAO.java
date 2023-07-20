package br.com.aps.fittracker.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
    //public Usuario buscarPorId(Long id);
    
    // return type é Usuario ou Optional<Usuario>? verificar
    public Usuario findByEmail(String email);
    
}
