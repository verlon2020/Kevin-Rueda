package com.KEVINRUEDA.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    private String anulado;

    private String puntajeTotal;

    private String comEscrita;

    private String razonCuantitativo;

    private String lecturaCritica;

    private String compeCiudadanas;

    private String ingles;

    private String formProyectos;

    private String penCientifico;

    private String disenoSoftware;

    // Constructor
    public Calificacion() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public String getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(String puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public String getComEscrita() {
        return comEscrita;
    }

    public void setComEscrita(String comEscrita) {
        this.comEscrita = comEscrita;
    }

    public String getRazonCuantitativo() {
        return razonCuantitativo;
    }

    public void setRazonCuantitativo(String razonCuantitativo) {
        this.razonCuantitativo = razonCuantitativo;
    }

    public String getLecturaCritica() {
        return lecturaCritica;
    }

    public void setLecturaCritica(String lecturaCritica) {
        this.lecturaCritica = lecturaCritica;
    }

    public String getCompeCiudadanas() {
        return compeCiudadanas;
    }

    public void setCompeCiudadanas(String compeCiudadanas) {
        this.compeCiudadanas = compeCiudadanas;
    }

    public String getIngles() {
        return ingles;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }

    public String getFormProyectos() {
        return formProyectos;
    }

    public void setFormProyectos(String formProyectos) {
        this.formProyectos = formProyectos;
    }

    public String getPenCientifico() {
        return penCientifico;
    }

    public void setPenCientifico(String penCientifico) {
        this.penCientifico = penCientifico;
    }

    public String getDisenoSoftware() {
        return disenoSoftware;
    }

    public void setDisenoSoftware(String disenoSoftware) {
        this.disenoSoftware = disenoSoftware;
    }
}