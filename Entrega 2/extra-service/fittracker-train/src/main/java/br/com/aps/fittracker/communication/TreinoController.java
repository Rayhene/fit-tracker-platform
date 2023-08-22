package br.com.aps.fittracker.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.aps.fittracker.model.controladores.ControladorTreino;
import br.com.aps.fittracker.model.treino.Treino;

@RequestMapping(path="/treinos")
@Controller
public class TreinoController {

    @Autowired
    private ControladorTreino fachada;


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


    
    //API TREINOS

    @PostMapping
    public String inserirTreino(@RequestBody Treino treino) {
        fachada.inserir(treino);
        return "Treino inserido com sucesso";
    }

    @PutMapping(path="/{id}")
    public String atualizarTreino(Treino treino) {
        fachada.atualizar(treino);
        return "Treino atualizado com sucesso";
    }

    @PostMapping(path="/atualizar")
    public String atualizarTreinoPost(@ModelAttribute("treino") Treino treino) {
        fachada.atualizar(treino);
        return "Treino atualizado com sucesso";
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Treino> listarTreinosUsuario(@PathVariable Long idUsuario) {
        return fachada.listarTreinosUsuario(idUsuario);
    }


    @DeleteMapping(path="/{id}")
    public String removerTreino(@PathVariable Long id) {
        fachada.remover(id);
        return "Treino removido com sucesso";
    }


}
