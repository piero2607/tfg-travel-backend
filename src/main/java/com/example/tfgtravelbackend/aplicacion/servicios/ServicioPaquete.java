package com.example.tfgtravelbackend.aplicacion.servicios;
import com.example.tfgtravelbackend.dominio.modelo.Paquete;
import com.example.tfgtravelbackend.dominio.puertos.entrada.ServicioPaquetePuerto;
import com.example.tfgtravelbackend.dominio.puertos.salida.RepositorioPaquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioPaquete implements ServicioPaquetePuerto {

    @Autowired
    private RepositorioPaquete repositorioPaquete;

    public List<Paquete> obtenerTodosLosPaquetes(){
        return repositorioPaquete.obtenerTodos();
    }

    public Optional<Paquete> buscarPaquetePorId(String id){
        return repositorioPaquete.buscarPorId(id);
    }

    public Paquete crearPaquete(String nombre,String destino, String descripcion,
                                Double precio,Integer duracionDias, Integer plazasDisponibles,
                                String urlImagen){
        if (nombre==null ||nombre.isEmpty()){
            throw new RuntimeException("El nombre del paquete es obligatorio");
        }
        if (precio<=0){
            throw new RuntimeException("El precio debe ser mayor que 0");
        }
        if (plazasDisponibles<=0){
            throw new RuntimeException("Debe haber al menos una plaza disponible");
        }
        Paquete nuevoPaquete=new Paquete(nombre,destino,descripcion,precio,duracionDias,plazasDisponibles,urlImagen);
        return repositorioPaquete.guardar(nuevoPaquete);
    }
    public Paquete actualizarPaquete(String id,String nombre,String destino,
                                     String descripcion,Double precio,Integer duracionDias,
                                     Integer plazasDisponibles,String urlImagen){
        Paquete paqueteExistente=repositorioPaquete.buscarPorId(id).
                orElseThrow(()->new RuntimeException("Paquete no encontrado con ID: " +id));

        paqueteExistente.setNombre(nombre);
        paqueteExistente.setDestino(destino);
        paqueteExistente.setDescripcion(descripcion);
        paqueteExistente.setPrecio(precio);
        paqueteExistente.setDuracionDias(duracionDias);
        paqueteExistente.setPlazasDisponibles(plazasDisponibles);
        paqueteExistente.setUrlimagen(urlImagen);

        return repositorioPaquete.guardar(paqueteExistente);
    }

    public void eliminarPaquete(String id){
        repositorioPaquete.buscarPorId(id).orElseThrow(()->new RuntimeException("Paquete no encontrado con Id: " +id));
        repositorioPaquete.eliminarPorId(id);
    }
    public List<Paquete> buscarPorDestino(String destino){
        if (destino==null||destino.isEmpty()){
            return repositorioPaquete.obtenerTodos();
        }
        return repositorioPaquete.buscarPorDestino(destino);
    }

    public List<Paquete> buscarPorPrecioMaximo(Double precioMaximo){
        if (precioMaximo==null||precioMaximo<=0){
            return repositorioPaquete.obtenerTodos();
        }
        return repositorioPaquete.buscarPorPrecioMenorQue(precioMaximo);
    }
}
