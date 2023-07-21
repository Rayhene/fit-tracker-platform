package br.com.aps.fittracker.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aps.fittracker.model.fachada.Fachada;
import br.com.aps.fittracker.model.treino.Treino;

@RestController
public class ListarTreinoController {

    @Autowired
    private Fachada fachada;

    
    
}
