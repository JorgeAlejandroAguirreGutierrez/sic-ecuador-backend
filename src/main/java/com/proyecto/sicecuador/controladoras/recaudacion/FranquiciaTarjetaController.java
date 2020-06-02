package com.proyecto.sicecuador.controladoras.recaudacion;

import com.proyecto.sicecuador.modelos.Respuesta;
import com.proyecto.sicecuador.modelos.recaudacion.FranquiciaTarjeta;
import com.proyecto.sicecuador.servicios.interf.recaudacion.IFranquiciaTarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/sicecuador/franquiciatarjeta")
public class FranquiciaTarjetaController {
    @Autowired
    private IFranquiciaTarjetaService servicio;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultar() {
        try {
            List<FranquiciaTarjeta> franquicias_tarjetas=servicio.consultar();
            Respuesta respuesta=new Respuesta(true,"Se consulto las franquicias de tarjetas", franquicias_tarjetas);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable("id") long id) {
        try {
            FranquiciaTarjeta franquicia_tarjeta=servicio.obtener(new FranquiciaTarjeta(id)).get();
            Respuesta respuesta=new Respuesta(true,"Se obtuvo una franquicia de tarjeta", franquicia_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody FranquiciaTarjeta _franquicia_tarjeta) {
        try {
            FranquiciaTarjeta franquicia_tarjeta=servicio.crear(_franquicia_tarjeta);
            Respuesta respuesta=new Respuesta(true,"Se creo una franquicia de tarjeta", franquicia_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@RequestBody FranquiciaTarjeta _franquicia_tarjeta) {
        try {
            FranquiciaTarjeta franquicia_tarjeta=servicio.actualizar(_franquicia_tarjeta);
            Respuesta respuesta=new Respuesta(true,"Se actualizo una franquicia de tarjeta", franquicia_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable("id") long id)  {
        try {
            FranquiciaTarjeta franquicia_tarjeta=servicio.eliminar(new FranquiciaTarjeta(id));
            Respuesta respuesta=new Respuesta(true,"Se elimino una franquicia de tarjeta", franquicia_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
