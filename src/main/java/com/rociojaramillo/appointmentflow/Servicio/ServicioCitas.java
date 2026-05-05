package com.rociojaramillo.appointmentflow.Servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rociojaramillo.appointmentflow.DTOs.CitasDTO;
import com.rociojaramillo.appointmentflow.Entidad.Cita;
import com.rociojaramillo.appointmentflow.Mapper.CitaMapper;
import com.rociojaramillo.appointmentflow.Repositorio.Repo_Citas;



@Service
public class ServicioCitas {

    @Autowired
    private Repo_Citas repo;

    public CitasDTO serv_Agenda(CitasDTO dto) {

        //Validación
        //¿La fecha es en el pasado?
        if (dto.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("No se pueden agendar citas en el pasado.");
        }

        //¿Está dentro del horario comercial? (Ej: 09:00 a 20:00)
        int hora = dto.getFechaHora().getHour();
        if (hora < 9 || hora >= 20) {
            throw new RuntimeException("El horario de atención es de 09:00 a 20:00.");
        }

        //Evitar solapamientos (Overlap)
        // Buscamos si existe alguna cita 30 minutos antes o después de la hora pedida
        LocalDateTime inicioRango = dto.getFechaHora().minusMinutes(29); //.minusMinutes(29) resta 29 minutos a esa hora. Si el usuario pidió un turno a las 10:00, esta variable valdrá 09:31.
        LocalDateTime finRango = dto.getFechaHora().plusMinutes(29); // .plusMinutes suma 29. Si el turno era a las 10:00, esta variable valdrá 10:29.
        
        // Necesitaremos crear este método en el repositorio
        boolean existeSolapamiento = repo.existsByFechaHoraBetween(inicioRango, finRango);
        
        if (existeSolapamiento) {
            throw new RuntimeException("Ya existe una cita programada para esa hora o muy cercana.");
        }

        // Si pasa todas las validaciones, procedemos a guardar
        // uso el mapper para convertir de dto a entidad
        Cita nuevaCita = CitaMapper.toEntity(dto);
        // guardo
        Cita guardada = repo.save(nuevaCita);
        // retornamos el DTO convertido de la entidad guardada ( ya con su ID)
        return CitaMapper.toDTO(guardada);
    }

    public List<CitasDTO> serv_Consulta(){
        return repo.findAll().stream()
        .map(CitaMapper::toDTO).collect(Collectors.toList());
    }

    public CitasDTO serv_ConsultaPorId(Long id) {
        Cita citaPorId = repo.findById(id).orElseThrow(()-> new RuntimeException("Cita con id: "+id+"no encontrada"));
        return CitaMapper.toDTO(citaPorId);
    }

    public String serv_Eliminar(Long id) {
        if (repo.existsById(id)){
            repo.deleteById(id);
            return "Cita eliminada correctamente";
        } else {
            return "Cita: "+ id + "No encontrada";
        }
    }



}
