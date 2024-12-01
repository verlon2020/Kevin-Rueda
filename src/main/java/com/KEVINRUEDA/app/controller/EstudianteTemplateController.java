package com.KEVINRUEDA.app.controller;

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
@RequestMapping("/estudiantes")
public class EstudianteTemplateController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @GetMapping("/")
    public String estudianteListTemplate(Model model) {
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        return "estudiantes-list";
    }

    @GetMapping("/new")
    public String estudiantesNewTemplate(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes-form";
    }

    @GetMapping("/edit/{id}")
    public String estudianteEditTemplate(@PathVariable("id") Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        model.addAttribute("estudiante", estudiante);
        return "estudiantes-form";
    }

    @PostMapping("/save")
    public String estudiantesSaveProcess(@ModelAttribute("estudiante") Estudiante estudiante) {
        validateEstudiante(estudiante);
        estudianteRepository.save(estudiante);
        return "vista-puntaje";
    }

    @PostMapping("/salvar")
    public String estudiantesSalvarProcess(@ModelAttribute("estudiante") Estudiante estudiante) {
        validateEstudiante(estudiante);
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes/";
    }

    @GetMapping("/delete/{id}")
    public String estudiantesDeleteProcess(@PathVariable("id") Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        Calificacion calificacion = calificacionRepository.findByEstudiante(estudiante);
        if (calificacion != null) {
            calificacionRepository.deleteById(calificacion.getId());
        }
        estudianteRepository.deleteById(id);
        return "redirect:/estudiantes/";
    }

    @GetMapping("/registro")
    public String registroTemplate(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "registro-estudiante";
    }

    @PostMapping("/ingresar")
    public String login(@RequestParam("numeroDocumento") String numeroDocumento,
                        @RequestParam("numeroRegistro") String numeroRegistro, Model model) {
        Estudiante estudiante = estudianteRepository.findByNumeroDocumentoAndNumeroRegistro(numeroDocumento, numeroRegistro);
        if (estudiante != null) {
            return "redirect:/estudiantes/resultado/" + numeroDocumento;
        } else {
            model.addAttribute("authenticationFailed", true);
            model.addAttribute("errorMessage", "No se encontró ningún estudiante");
            return "login-general";
        }
    }

    @GetMapping("/resultado/{numeroDocumento}")
    public String estudianteResultTemplate(@PathVariable("numeroDocumento") String numeroDocumento, Model model) {
        Estudiante estudiante = estudianteRepository.findByNumeroDocumento(numeroDocumento);
        if (estudiante == null) {
            throw new NotFoundException("Estudiante no encontrado");
        }
        model.addAttribute("calificacion", calificacionRepository.findByEstudiante(estudiante));
        estudiante.setRevisado("si");
        estudianteRepository.save(estudiante);
        return "resultados-estudiante";
    }

    @GetMapping("/detallado/{numeroDocumento}")
    public String estudianteDetailTemplate(@PathVariable("numeroDocumento") String numeroDocumento, Model model) {
        Estudiante estudiante = estudianteRepository.findByNumeroDocumento(numeroDocumento);
        if (estudiante == null) {
            throw new NotFoundException("Estudiante no encontrado");
        }
        model.addAttribute("calificacion", calificacionRepository.findByEstudiante(estudiante));
        return "resultado-detallado";
    }

    private void validateEstudiante(Estudiante estudiante) {
        if (estudiante.getId() == null || estudiante.getId() == 0) {
            estudiante.setId(null);
        }
        if (estudiante.getRevisado() == null || estudiante.getRevisado().isEmpty()) {
            estudiante.setRevisado("no");
        }
    }
}
