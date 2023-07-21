package br.com.aps.fittracker.model.usuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.aps.fittracker.model.usuario.Usuario;

@Component
public class UsuarioRepositoryJSON implements IUsuarioRepository {

    private final String jsonFilePath = "usuarios.json";    // Caminho do arquivo JSON
    private List<Usuario> usuarios = new ArrayList<>();     // Lista de usuários em memória
    private ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper para serialização/desserialização JSON


    public UsuarioRepositoryJSON() {
        // Carrega os usuários do arquivo JSON no construtor
        usuarios = lerUsuariosDoJson();
    }

    @Override
    public void inserir(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuariosNoJson();
    }

    @Override
    public void atualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario);
                salvarUsuariosNoJson();
                break;
            }
        }
    }

    @Override
    public void remover(Long id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
        salvarUsuariosNoJson();
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Usuario get(Long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método para ler os usuários do arquivo JSON
    private List<Usuario> lerUsuariosDoJson() {
        File file = new File(jsonFilePath);
        if (file.exists()) {
            try {
                return Arrays.asList(objectMapper.readValue(file, Usuario[].class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Caso o arquivo não exista ou haja uma exceção, retornamos uma lista vazia
        return new ArrayList<>();
    }

    // Método para salvar os usuários no arquivo JSON
    private void salvarUsuariosNoJson() {
        try {
            objectMapper.writeValue(new File(jsonFilePath), usuarios);
        } catch (IOException e) {
            e.printStackTrace();
            // Lida com a exceção de IO, se necessário
        }
    }
}
