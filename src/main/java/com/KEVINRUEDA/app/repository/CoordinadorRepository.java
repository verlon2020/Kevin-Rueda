package com.KEVINRUEDA.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KEVINRUEDA.app.entity.Coordinador;

public interface CoordinadorRepository extends JpaRepository<Coordinador, Long> {

    Coordinador findByUserAndPassword(String user, String password);
}
