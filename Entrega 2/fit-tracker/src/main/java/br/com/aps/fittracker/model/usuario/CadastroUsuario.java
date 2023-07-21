package br.com.aps.fittracker.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;

// Classe Cadastro
public class CadastroUsuario {
    
    @Autowired //injeção de dependência
    private IUsuarioRepository usuarioRepository;

    public void inserir(Usuario usuario) {
        // realizar alguma lógica de verificação para o cadastro
        usuarioRepository.inserir(usuario);
    }

    public void atualizar(Usuario usuario) {
        // realizar alguma lógica de verificação para o cadastro
        usuarioRepository.atualizar(usuario);
    }

    public void remover(Long id) {
        // realizar alguma lógica de verificação para o cadastro
        usuarioRepository.remover(id);
    }

    public Usuario buscarPorEmail(String email) {
        // realizar alguma lógica de verificação para o cadastro
        return usuarioRepository.buscarPorEmail(email);
    }
    
    public Usuario get(Long id) {
        return usuarioRepository.get(id);
    }


}
