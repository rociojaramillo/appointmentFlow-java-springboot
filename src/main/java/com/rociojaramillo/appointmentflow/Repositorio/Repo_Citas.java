package com.rociojaramillo.appointmentflow.Repositorio;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rociojaramillo.appointmentflow.Entidad.Cita;

public interface Repo_Citas extends JpaRepository<Cita,Long> {
// al extender de jpaRepository spring me da los métodos findAll, save, delete, etc. 

// Spring genera el SQL automáticamente basándose en el nombre del método
    boolean existsByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

}
