package com.example.tfgtravelbackend.infraestructura.adaptadores.entrada;

import com.example.tfgtravelbackend.dominio.modelo.Reserva;
import com.example.tfgtravelbackend.aplicacion.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorReserva {

    @Autowired
    private ServicioReserva servicioReserva;

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        Reserva nuevaReserva = servicioReserva.crearReserva(
                reservaDTO.getPaqueteId(),
                reservaDTO.getClienteNombre(),
                reservaDTO.getClienteEmail(),
                reservaDTO.getNumeroPersonas()
        );
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Reserva> obtenerTodasLasReservas() {
        return servicioReserva.obtenerTodasLasReservas();
    }

    @GetMapping("/usuario")
    public List<Reserva> obtenerReservasPorEmail(@RequestParam String email) {
        return servicioReserva.obtenerReservasPorEmail(email);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Reserva> confirmarReserva(@PathVariable String id) {
        Reserva reservaConfirmada = servicioReserva.confirmarReserva(id);
        return ResponseEntity.ok(reservaConfirmada);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable String id) {
        Reserva reservaCancelada = servicioReserva.cancelarReserva(id);
        return ResponseEntity.ok(reservaCancelada);
    }
}