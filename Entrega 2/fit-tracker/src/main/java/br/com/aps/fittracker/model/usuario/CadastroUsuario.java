package br.com.aps.fittracker.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.aps.fittracker.model.factory.IRepositorioFactory;
import br.com.aps.fittracker.model.factory.RepositorioBDRFactory;
import br.com.aps.fittracker.model.factory.RepositorioFileFactory;

@Component
public class CadastroUsuario {
    
    //@Autowired //injeção de dependência
    private IUsuarioRepository usuarioRepository;
    private IRepositorioFactory repositorioFactory;

    @Autowired
    public CadastroUsuario(@Value("${app.repository.type}") String tipoRepositorio) {
        if(tipoRepositorio.equals("json")){
            this.repositorioFactory = RepositorioFileFactory.getInstance();
        } else { //default is bdr
            this.repositorioFactory = RepositorioBDRFactory.getInstance();
        }
            
        this.usuarioRepository = repositorioFactory.criarRepositorioUsuarios();
    }

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
