package br.com.aps.fittracker.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;

public class ControladorUsuario {
    
    @Autowired
    private CadastroUsuario usuarioCadastro;

    public void inserir(Usuario usuario) {
        usuarioCadastro.inserir(usuario);
    }

    public void atualizar(Usuario usuario) {
        usuarioCadastro.atualizar(usuario);
    }

    public void remover(Long id) {
        usuarioCadastro.remover(id);
    }

    public Usuario buscarPorEmail(String email) {
        // realizar alguma lógica de verificação para o controlador
        //String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        
        return usuarioCadastro.buscarPorEmail(email);
    }

    public Usuario getUsuario(Long id) {
        return usuarioCadastro.get(id);
    }


}
