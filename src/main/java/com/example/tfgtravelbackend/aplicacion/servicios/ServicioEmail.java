package com.example.tfgtravelbackend.aplicacion.servicios;
import com.example.tfgtravelbackend.dominio.modelo.Reserva;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ServicioEmail {
    @Async
    public CompletableFuture<Void> enviarEmailConfirmacion(String email,String nombre,Reserva reserva){
        log.info("📧 Enviando email a {} en el hilo: {}",email, Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        log.info("✅ Email enviado a: {} - Reserva: {}", email, reserva.getId());
        log.info("   Contenido: Hola {}, tu reserva está PENDIENTE. Confirma en 30 minutos.", nombre);
        return CompletableFuture.completedFuture(null);
    }
}
