package com.example.tfgtravelbackend.aplicacion.servicios;

import org.springframework.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;
import com.example.tfgtravelbackend.dominio.modelo.Paquete;
import com.example.tfgtravelbackend.dominio.modelo.Reserva;
import com.example.tfgtravelbackend.dominio.puertos.salida.RepositorioPaquete;
import com.example.tfgtravelbackend.dominio.puertos.salida.RepositorioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ServicioReserva {
    @Autowired
    private RepositorioReserva repositorioReserva;

    @Autowired
    private RepositorioPaquete repositorioPaquete;

    @Autowired
    private ServicioEmail servicioEmail;

    public Reserva crearReserva(String paqueteId, String clienteNombre,String clienteEmail,int numeroPersonas){
        Paquete paquete=repositorioPaquete.buscarPorId(paqueteId)
                .orElseThrow(()-> new RuntimeException("Paquete no encontardo"));

        if (paquete.getPlazasDisponibles()< numeroPersonas){
            throw new RuntimeException("No hay suficientes plazas disponibles");
        }

        Double precioTotal=paquete.getPrecio()*numeroPersonas;

        Reserva reserva=new Reserva(paqueteId,clienteNombre,clienteEmail,numeroPersonas,precioTotal);

        paquete.reducirPlazas(numeroPersonas);
        repositorioPaquete.guardar(paquete);

        Reserva reservaGuardada=repositorioReserva.guardar(reserva);

        servicioEmail.enviarEmailConfirmacion(clienteEmail,clienteNombre,reservaGuardada);

        return reservaGuardada;
    }

    public Reserva confirmarReserva(String reservaId){
        Reserva reserva=repositorioReserva.buscarPorId(reservaId)
                .orElseThrow(()->new RuntimeException("Reserva no encontrada"));

        reserva.confirmar();
        return repositorioReserva.guardar(reserva);
    }

    public Reserva cancelarReserva(String reservaId){
        Reserva reserva=repositorioReserva.buscarPorId(reservaId)
                .orElseThrow(()->new RuntimeException("Reserva no encontrada"));
        reserva.cancelar();
        return repositorioReserva.guardar(reserva);
    }

    public List<Reserva> obtenerReservasPorEmail(String email){
        return repositorioReserva.buscarPorEmail(email);
    }

    public List<Reserva> obtenerTodasLasReservas(){
        return repositorioReserva.obtenerTodas();
    }

    @Scheduled(fixedDelay = 300000)
    public void liberarReservasExpiradas(){
        log.info("🕐 Ejecutando tarea programada en hilo: {}", Thread.currentThread().getName());
        List<Reserva> reservaExpiradas = repositorioReserva.buscarReservasExpiradas();
        for (Reserva reserva: reservaExpiradas){
            log.info("Liberando reserva expirada: {}", reserva.getId());
            reserva.cancelar();
            repositorioReserva.guardar(reserva);

            repositorioPaquete.buscarPorId(reserva.getPaqueteId()).ifPresent(paquete ->{
                paquete.setPlazasDisponibles(paquete.getPlazasDisponibles() + reserva.getNumeroPersonas());
                repositorioPaquete.guardar(paquete);
            } );
        }

    }

}
