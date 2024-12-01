package com.KEVINRUEDA.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.KEVINRUEDA.app.entity.Calificacion;
import com.KEVINRUEDA.app.entity.Estudiante;
import com.KEVINRUEDA.app.exception.NotFoundException;
import com.KEVINRUEDA.app.repository.CalificacionRepository;
import com.KEVINRUEDA.app.repository.EstudianteRepository;

@Controller
@RequestMapping("/calificaciones")
public class CalificacionTemplateController {

    @Autowired
    private CalificacionRepository calificacionRepository;
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/")
    public String calificacionListTemplate(Model model) {
        model.addAttribute("calificaciones", calificacionRepository.findAll());
        return "calificaciones-list";
    }

    @GetMapping("/new")
    public String calificacionesNewTemplate(Model model) {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("calificacion", new Calificacion());
        return "calificaciones-form";
    }

    @GetMapping("/edit/{id}")
    public String calificacionEditTemplate(@PathVariable("id") Long id, Model model) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Calificación no encontrada"));
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        model.addAttribute("calificacion", calificacion);
        model.addAttribute("estudiantes", estudiantes);
        return "calificaciones-form";
    }

    @PostMapping("/save")
    public String calificacionesSaveProcess(@ModelAttribute("calificacion") Calificacion calificacion) {
        calificacionRepository.save(calificacion);
        return "redirect:/calificaciones/";
    }

    @GetMapping("/delete/{id}")
    public String calificacionesDeleteProcess(@PathVariable("id") Long id) {
        calificacionRepository.deleteById(id);
        return "redirect:/calificaciones/";
    }
}
