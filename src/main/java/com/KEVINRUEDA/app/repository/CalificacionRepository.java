package com.KEVINRUEDA.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KEVINRUEDA.app.entity.Calificacion;
import com.KEVINRUEDA.app.entity.Estudiante;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    Calificacion findByEstudiante(Estudiante estudiante);
}
