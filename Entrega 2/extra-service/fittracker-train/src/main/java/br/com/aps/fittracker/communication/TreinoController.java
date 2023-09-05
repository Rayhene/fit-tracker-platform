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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.aps.fittracker.model.controladores.ControladorTreino;
import br.com.aps.fittracker.model.programado.ExercicioProgramado;
import br.com.aps.fittracker.model.treino.Treino;



@RequestMapping(path="/treinos")
@Controller
public class TreinoController {

    @Autowired
    private ControladorTreino fachada;


    @GetMapping("")
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

    @GetMapping("/editartreino/{idTreino}")
    public String editarTreino(@PathVariable("idTreino") Long idTreino, Model model) {
        try {
            // Retrieve the treino data based on the idTreino
            Treino treino = fachada.buscarTreinoPeloId(idTreino);
            //ExercicioProgramado exercicio = new ExercicioProgramado();

            // Add the treino data to the model to be used in the view (editartreino.html)
            model.addAttribute("treino", treino);
            //model.addAttribute("exercicio", exercicio);

            return "editartreino"; // Return the name of the view file (editartreino.html)
        } catch (IllegalArgumentException e) {
            return "error";
        }
    }

    @PostMapping(path="/editartreino")
    public String atualizarTreinoNomeDescricao(@ModelAttribute("treino") Treino treino, 
    RedirectAttributes redirectAttributes) {
        try {
            fachada.atualizarNomeDescricao(treino);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addAttribute("errorMsg", "Não foi possível alterar o treino.");
        }
        redirectAttributes.addAttribute("sucMsg", "Treino alterado com sucesso.");
        return "redirect:/treinos";
    }

    @PostMapping("/deletartreino/{idTreino}")
    public String deletarTreino(@PathVariable("idTreino") Long idTreino, RedirectAttributes redirectAttributes) {
        try {
            fachada.remover(idTreino);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addAttribute("errorMsg", "Não foi possível excluir o treino.");
        }
        redirectAttributes.addAttribute("sucMsg", "Treino excluído com sucesso.");
        return "redirect:/treinos";
    }

    @PostMapping(path="/editarexercicio")
    public String atualizarTreinoNomeDescricao( @RequestParam Long exercicio_id,
                                                @RequestParam String exercicio_nome,
                                                @RequestParam int exercicio_series,
                                                @RequestParam int exercicio_repeticoes,
                                                @RequestParam double exercicio_carga,
                                                @RequestParam int exercicio_descanso,
                                                @RequestParam String idTreino,
                                                RedirectAttributes redirectAttributes, Model model) {
        try {
            ExercicioProgramado exercicio = new ExercicioProgramado(exercicio_nome, exercicio_series, exercicio_repeticoes, exercicio_carga, exercicio_descanso, exercicio_id);
            fachada.atualizarExercicioProgramado(exercicio);
            model.addAttribute("successMsg", "Exercício atualizado com sucesso.");
            redirectAttributes.addAttribute("sucMsg", "Exercício atualizado com sucesso.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMsg", "Não foi possível alterar o exercício.");
            redirectAttributes.addAttribute("errorMsg", "Não foi possível alterar o exercício.");
        }
        return "redirect:/treinos/editartreino/"+idTreino;
    }

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









    
    //API TREINOS

    @PostMapping
    public String inserirTreinoAPI(@RequestBody Treino treino) {
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
