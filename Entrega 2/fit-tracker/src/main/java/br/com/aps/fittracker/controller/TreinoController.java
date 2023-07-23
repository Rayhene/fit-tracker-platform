package br.com.aps.fittracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.aps.fittracker.model.fachada.Fachada;
import br.com.aps.fittracker.model.treino.Treino;

@RequestMapping(path="/treinos")
@RestController
public class TreinoController {

    @Autowired
    private Fachada fachada;


    

    @PostMapping
    public String inserirTreino(@RequestBody Treino treino) {
        fachada.inserirTreino(treino);
        return "Treino inserido com sucesso";
    }

    @PutMapping(path="/{id}")
    public String atualizarTreino(Treino treino) {
        fachada.atualizarTreino(treino);
        return "Treino atualizado com sucesso";
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Treino> listarTreinosUsuario(@PathVariable Long idUsuario) {
        return fachada.listarTreinosUsuario(idUsuario);
    }


    @DeleteMapping(path="/{id}")
    public String removerTreino(@PathVariable Long id) {
        fachada.removerTreino(id);
        return "Treino removido com sucesso";
    }


}
