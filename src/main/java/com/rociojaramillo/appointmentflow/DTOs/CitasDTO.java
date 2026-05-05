package com.rociojaramillo.appointmentflow.DTOs;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;

@NoArgsConstructor //reempplaza el constructor vacio para que Jackson pueda crear el objeto desde JSON


public class CitasDTO {
    private Long id;
    private String nombreCliente;
    private String servicio; // ejemplo: corte de pelo, consulta
    private LocalDateTime fechaHora;

    public CitasDTO(Long id, String nombreCliente, String servicio, LocalDateTime fechaHora){
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    



}
