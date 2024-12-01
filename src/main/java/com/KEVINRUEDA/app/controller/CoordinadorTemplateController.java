package com.KEVINRUEDA.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.KEVINRUEDA.app.entity.Coordinador;
import com.KEVINRUEDA.app.exception.NotFoundException;
import com.KEVINRUEDA.app.repository.CoordinadorRepository;

@Controller
@RequestMapping("/coordinadores")
public class CoordinadorTemplateController {

    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @GetMapping("/")
    public String CoordinadorListTemplate(Model model) {
        model.addAttribute("coordinadores", coordinadorRepository.findAll());
        return "coordinadores-list";
    }

    @GetMapping("/new")
    public String coordinadoresNewTemplate(Model model) {
        model.addAttribute("coordinador", new Coordinador());
        return "coordinadores-form";
    }

    @GetMapping("/edit/{id}")
    public String CoordinadorEditTemplate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("coordinador", coordinadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coordinador no encontrado")));
        return "coordinadores-form";
    }

    @PostMapping("/save")
    public String coordinadoresSaveProcess(@ModelAttribute("coordinador") Coordinador coordinador) {
        if (coordinador.getId() == null) {
            coordinador.setId(null);
        }
        coordinadorRepository.save(coordinador);
        return "redirect:/home/";
    }

    @PostMapping("/salvar")
    public String CoordinadoresSalvarProcess(@ModelAttribute("coordinador") Coordinador coordinador) {
        if (coordinador.getId() == null) {
            coordinador.setId(null);
        }
        coordinadorRepository.save(coordinador);
        return "redirect:/coordinadores/";
    }

    @GetMapping("/delete/{id}")
    public String coordinadoresDeleteProcess(@PathVariable("id") Long id) {
        coordinadorRepository.deleteById(id);
        return "redirect:/coordinadores/";
    }

    @GetMapping("/registro")
    public String registroTemplate(Model model) {
        model.addAttribute("coordinador", new Coordinador());
        return "registro-coordinador";
    }

    @PostMapping("/ingresar")
    public String login(@RequestParam("user") String user, @RequestParam("password") String password, Model model) {
        Coordinador coordinador = coordinadorRepository.findByUserAndPassword(user, password);
        if (coordinador != null) {
            return "home";
        } else {
            model.addAttribute("authenticationFailed", true);
            model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
            return "login-general";
        }
    }
}
