package br.com.aps.fittracker.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aps.fittracker.model.fachada.Fachada;
import br.com.aps.fittracker.model.treino.Treino;

@RequestMapping(path="/treinos")
@RestController
public class TreinoController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    public String inserirTreino(@RequestBody Map<String, String> requestBody) {
        String nome = requestBody.get("nome");
        System.out.println(nome);   
        fachada.inserirTreino(nome);
        return "Treino inserido com sucesso";
    }

    @PutMapping(path="/{id}")
    public String atualizarTreino(Treino treino) {
        fachada.atualizarTreino(treino);
        return "Treino atualizado com sucesso";
    }


    @DeleteMapping(path="/{id}")
    public String removerTreino(@PathVariable Long id) {
        fachada.removerTreino(id);
        return "Treino removido com sucesso";
    }


}
