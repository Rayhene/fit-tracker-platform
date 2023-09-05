package br.com.aps.fittracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.aps.fittracker.model.exercicio.Exercicio;
import br.com.aps.fittracker.model.controladores.ControladorTreino;
import br.com.aps.fittracker.model.treino.Treino;

@Controller
@RequestMapping(path="/")
public class AdicionarTreinoController {

    @Autowired
    private ControladorTreino fachada;

    @GetMapping("/addtreino")
    public String adicionarTreino() {
        return "addtreino";
    }

    @PostMapping("/addtreino")
    public ResponseEntity<String> inserirTreino(@RequestBody Treino treino) {
        try {
            fachada.inserir(treino);
            return ResponseEntity.ok("Treino inserido com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /*
    @GetMapping
    public String listarTreinosUsuario(@CookieValue(value = "usuarioId", required = false) String usuarioId, Model model) {
        List<Treino> treinos = new ArrayList<>();
        String msg;
        if (usuarioId == null) {
            // Handle the case where userId cookie is not present
            // For example, you can redirect the user to a login page or return an error message
            msg = "Ainda não há treinos cadastrados";
        } else {
            treinos = fachada.listarTreinosUsuario(Long.parseLong(usuarioId));
            msg = "Seus treinos cadastrados";
        }
        model.addAttribute("mensagem", msg);
        model.addAttribute("treinos", treinos);
       
        return "treinos";
    }
     */


    
}
