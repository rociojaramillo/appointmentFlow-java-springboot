package com.rociojaramillo.appointmentflow.Mapper;

import com.rociojaramillo.appointmentflow.DTOs.CitasDTO;
import com.rociojaramillo.appointmentflow.Entidad.Cita;

public class CitaMapper {
// estatica, no necesita ser instanciada

    public static CitasDTO toDTO(Cita cita){
        return new CitasDTO(cita.getId(), cita.getNombreCliente(),
        cita.getServicio(), cita.getFechaHora());
    }

    public static Cita toEntity(CitasDTO dto){
        Cita cita = new Cita();
        cita.setId(dto.getId());
        cita.setNombreCliente(dto.getNombreCliente());
        cita.setServicio(dto.getServicio());
        cita.setFechaHora(dto.getFechaHora());
        return cita;
    }

}
