package com.KEVINRUEDA.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KEVINRUEDA.app.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Estudiante findByNumeroDocumentoAndNumeroRegistro(String numeroDocumento, String numeroRegistro);

    Estudiante findByNumeroDocumento(String numeroDocumento);
}
