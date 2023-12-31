package br.com.aps.fittracker.model.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aps.fittracker.model.usuario.IUsuarioRepository;

@Configuration
public class FactoryConfig {

    // Bean para passar o tipo de repositório na inicialização do Factory
    @Bean
    public IRepositorioFactory repositorioFactory(@Value("${app.repository.type}") String tipoRepositorio) {
        if(tipoRepositorio.equals("json")){
            return RepositorioFileFactory.getInstance();
    
        } else { //default is bdr
            return RepositorioBDRFactory.getInstance();
        }
    }

    // Bean para passar o IUsuarioRepository na inicialização do CadastroUsuario
    @Bean 
    public IUsuarioRepository usuarioRepositoryByFactory(IRepositorioFactory repositorioFactory) {
        return repositorioFactory.criarRepositorioUsuarios();
    }

}