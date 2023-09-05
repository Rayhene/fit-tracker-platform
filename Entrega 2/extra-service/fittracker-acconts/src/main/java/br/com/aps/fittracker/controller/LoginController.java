package br.com.aps.fittracker.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;


import br.com.aps.fittracker.model.controladores.ControladorUsuario;
import br.com.aps.fittracker.model.usuario.Usuario;

@RequestMapping(path="/account")
@Controller
public class LoginController {

    @Autowired
    private ControladorUsuario fachada;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${gateway_treino_url}")
    private String treinosUrl;

    //@Value("${treino_service_url}")
    //private String treinosUrl;

    //@ModelAttribute("treinosUrl")
    private String getTreinosUrl() {
        //ServiceInstance sInstance = discoveryClient.getInstances("gateway").iterator().next();        
        //return String.format(
        //       "%s://%s:%s/treinos", sInstance.getScheme(), sInstance.getHost(), sInstance.getPort());

        //String routePath = "lb://treino/treinos";
        //String routePath = "http://localhost:8084/treinos";
        String routePath = treinosUrl;
        return routePath;
    }

    @GetMapping(path="/login")
    public String login() {
        return "login";
    }

    @PostMapping(path="/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        Map<String, String> responseData = new HashMap<>();

        try {
            String email = loginRequest.get("email");
            String senha = loginRequest.get("senha");
            Usuario usuario = fachada.login(email, senha);
            System.out.println("\n#########\n" + getTreinosUrl() + "\n#########\n");
            responseData.put("redirect", getTreinosUrl());
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
            responseData.put("redirect", getTreinosUrl());
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
            fachada.inserir(usuario);
            return ResponseEntity.ok("Usuário inserido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage()); //405
        }
    }

    @GetMapping(path="/signup/back")
    public ResponseEntity<Map<String, String>> backToLogin() {
        Map<String, String> responseData = new HashMap<>();
        responseData.put("redirect", "login");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/usuario/existe/{usuarioId}")
    @ResponseBody
    public Map<String, Boolean> existeUsuario(@PathVariable Long usuarioId)
            throws InterruptedException {
        //Thread.sleep(ThreadLocalRandom.current().nextInt(1, 4000));
        return Collections.singletonMap("existeUsuario", fachada.existeUsuario(usuarioId));
    }

    public static void main(String args[]) {
        System.out.println("Teste LoginCOntroller");
    }
    
}
