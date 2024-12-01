package com.KEVINRUEDA.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.KEVINRUEDA.app.entity.Coordinador;
import com.KEVINRUEDA.app.exception.NotFoundException;
import com.KEVINRUEDA.app.repository.CoordinadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/coordinadores")
public class CoordinadorController {

    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @GetMapping("/")
    public List<Coordinador> getAllCoordinadores() {
        return coordinadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Coordinador getCoordinadorById(@PathVariable Long id) {
        return coordinadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
    }

    @PostMapping("/")
    public Coordinador saveCoordinador(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Coordinador coordinador = mapper.convertValue(body, Coordinador.class);
        return coordinadorRepository.save(coordinador);
    }

    @PutMapping("/{id}")
    public Coordinador updateCoordinador(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Coordinador existingCoordinador = coordinadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
        
        ObjectMapper mapper = new ObjectMapper();
        Coordinador coordinador = mapper.convertValue(body, Coordinador.class);
        coordinador.setId(id);
        
        return coordinadorRepository.save(coordinador);
    }

    @DeleteMapping("/{id}")
    public Coordinador deleteCoordinador(@PathVariable Long id) {
        Coordinador coordinador = coordinadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
        coordinadorRepository.deleteById(id);
        return coordinador;
    }
}
