package com.example.tfgtravelbackend.dominio.puertos.entrada;
import com.example.tfgtravelbackend.dominio.modelo.Reserva;
import java.util.List;

public interface ServicioReservaPuerto {
    Reserva crearReserva(String paqueteId, String clienteNombre,
                         String clienteEmail, int numeroPersonas);

    Reserva confirmarReserva(String reservaId);

    Reserva cancelarReserva(String reservaId);

    List<Reserva> obtenerReservasPorEmail(String email);

    List<Reserva> obtenerTodasLasReservas();
}
