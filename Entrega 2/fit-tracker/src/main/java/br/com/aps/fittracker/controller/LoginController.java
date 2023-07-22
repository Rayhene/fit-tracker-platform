package br.com.aps.fittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aps.fittracker.model.fachada.Fachada;
import br.com.aps.fittracker.model.usuario.Usuario;

@RequestMapping(path="/")
@RestController
public class LoginController {

    @Autowired
    private Fachada fachada;

    @GetMapping
    public String hello() {
        return "Hello World!";
    }

    @PostMapping(path="/signup")
    public String inserirUsuario(@RequestBody Usuario usuario) {
        try {
            fachada.inserirUsuario(usuario);
            return "Usuário inserido com sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(path="/login")
    public String login(@RequestParam(name = "email") String email, @RequestParam(name = "senha") String senha) {
        if (fachada.login(email, senha)) {
            return "Login realizado com sucesso";
        } else {
            return "Login ou senha inválidos";
        }
    }

    
}
