package com.rociojaramillo.appointmentflow.Controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rociojaramillo.appointmentflow.DTOs.CitasDTO;
import com.rociojaramillo.appointmentflow.Servicio.ServicioCitas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*") // para que el html hable con la api
public class Controller {

    @Autowired //para la inyeccion
    private ServicioCitas servicio;

    @GetMapping
    public List<CitasDTO> obtenerTodas() {
        return servicio.serv_Consulta();
    }
    
    @GetMapping("/{id}")
    public CitasDTO obtenerPorId(@PathVariable Long id) {
        return servicio.serv_ConsultaPorId(id);
    }

    @PostMapping 
    public CitasDTO agendarCita(@RequestBody CitasDTO dto) {
        return servicio.serv_Agenda(dto);
    }
    
    @DeleteMapping("/{id}")
    public String eliminarCita(@PathVariable Long id) {
        servicio.serv_Eliminar(id);
        return "Cita eliminada correctamente";
    }

}
