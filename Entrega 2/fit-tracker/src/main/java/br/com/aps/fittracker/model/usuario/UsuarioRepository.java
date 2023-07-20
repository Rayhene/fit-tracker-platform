package br.com.aps.fittracker.model.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aps.fittracker.model.treino.Treino;

public class UsuarioRepository implements IUsuarioRepository{

    @Autowired //injeção de dependência
    private UsuarioDAO usuarioDAO; 
    
    public void inserir(Usuario usuario) {
        usuarioDAO.save(usuario);
    }
    public void atualizar(Usuario usuario) {
        usuarioDAO.save(usuario);
    }
    public void remover(Long id) {
        usuarioDAO.deleteById(id);
        Optional<Usuario> option = usuarioDAO.findById(id);

        if(option.isPresent()){
            usuarioDAO.delete(option.get());
        }
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }
}