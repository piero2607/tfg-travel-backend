package com.example.tfgtravelbackend.dominio.puertos.salida;
import com.example.tfgtravelbackend.dominio.modelo.Reserva;
import java.util.List;
import java.util.Optional;

public interface RepositorioReserva {
    List<Reserva> obtenerTodas();

    Optional<Reserva> buscarPorId(String id);

    List<Reserva> buscarPorEmail(String email);

    Reserva guardar(Reserva reserva);

    void eliminarPorId(String id);

    List<Reserva> buscarReservasExpiradas();
}
