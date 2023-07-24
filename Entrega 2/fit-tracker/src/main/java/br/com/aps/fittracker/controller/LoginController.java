package br.com.aps.fittracker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.aps.fittracker.model.fachada.Fachada;
import br.com.aps.fittracker.model.usuario.Usuario;

@RequestMapping(path="/")
@Controller
public class LoginController {

    @Autowired
    private Fachada fachada;

    @GetMapping(path="/login")
    public String login() {
        return "/login";
    }

    @PostMapping(path="/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        Map<String, String> responseData = new HashMap<>();

        try {
            String email = loginRequest.get("email");
            String senha = loginRequest.get("senha");
            Usuario usuario = fachada.login(email, senha);
            responseData.put("redirect", "/treinos");
            responseData.put("usuarioId", Long.toString(usuario.getId()));
            return ResponseEntity.ok(responseData);
        } catch (IllegalArgumentException e) {
            responseData.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData); //401
        }  
    }

    @PostMapping(path="/login/google")
    public ResponseEntity<Map<String, String>> loginGoogle(@RequestBody String idToken) {
        Map<String, String> responseData = new HashMap<>();
        Usuario usuario = null;
        try {
            usuario = fachada.loginGoogle(idToken);
            responseData.put("redirect", "/treinos");
            responseData.put("usuarioId", Long.toString(usuario.getId()));
            return ResponseEntity.ok(responseData);
        } catch (IllegalArgumentException e) {
           responseData.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData); //401
        }
    }

    @GetMapping(path="/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping(path="/signup")
    public ResponseEntity<String> inserirUsuario(@RequestBody Usuario usuario) {
        try {
            fachada.inserirUsuario(usuario);
            return ResponseEntity.ok("Usuário inserido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage()); //405
        }
    }

    @GetMapping(path="/signup/back")
    public ResponseEntity<Map<String, String>> backToLogin() {
        Map<String, String> responseData = new HashMap<>();
        responseData.put("redirect", "/login");
        return ResponseEntity.ok(responseData);
    }
    
}
