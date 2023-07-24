package br.com.aps.fittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.aps.fittracker.model.fachada.Fachada;
import br.com.aps.fittracker.model.programado.ExercicioProgramado;
import br.com.aps.fittracker.model.treino.Treino;

@RequestMapping(path="/")
@Controller
public class EditarTreinoController {

    @Autowired
    Fachada fachada;
    
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
            fachada.atualizarTreinoNomeDescricao(treino);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addAttribute("errorMsg", "Não foi possível alterar o treino.");
        }
        redirectAttributes.addAttribute("sucMsg", "Treino alterado com sucesso.");
        return "redirect:/treinos";
    }

    @PostMapping("/deletartreino/{idTreino}")
    public String deletarTreino(@PathVariable("idTreino") Long idTreino, RedirectAttributes redirectAttributes) {
        try {
            fachada.removerTreino(idTreino);
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
        return "redirect:/editartreino/"+idTreino;
    }

    

}
