package com.proyecto.sicecuador.controladoras.recaudacion;

import com.proyecto.sicecuador.modelos.Respuesta;
import com.proyecto.sicecuador.modelos.recaudacion.OperadorTarjeta;
import com.proyecto.sicecuador.servicios.interf.recaudacion.IOperadorTarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/sicecuador/operadortarjeta")
public class OperadorTarjetaController {

    @Autowired
    private IOperadorTarjetaService servicio;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultar() {
        try {
            List<OperadorTarjeta> operadores_tarjetas=servicio.consultar();
            Respuesta respuesta=new Respuesta(true,"Se consulto las operadores de tarjetas", operadores_tarjetas);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/tipo/{tipo}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultar(@PathVariable("tipo") String tipo) {
        try {
            List<OperadorTarjeta> operadores_tarjetas=servicio.consultarTipo(new OperadorTarjeta(tipo));
            Respuesta respuesta=new Respuesta(true,"Se consulto las operadores de tarjetas", operadores_tarjetas);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable("id") long id) {
        try {
            OperadorTarjeta operador_tarjeta=servicio.obtener(new OperadorTarjeta(id)).get();
            Respuesta respuesta=new Respuesta(true,"Se obtuvo un operador de tarjeta", operador_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody OperadorTarjeta _operador_tarjeta) {
        try {
            OperadorTarjeta operador_tarjeta=servicio.crear(_operador_tarjeta);
            Respuesta respuesta=new Respuesta(true,"Se creo un operador de tarjeta", operador_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@RequestBody OperadorTarjeta _operador_tarjeta) {
        try {
            OperadorTarjeta operador_tarjeta=servicio.actualizar(_operador_tarjeta);
            Respuesta respuesta=new Respuesta(true,"Se actualizo un operador de tarjeta", operador_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable("id") long id)  {
        try {
            OperadorTarjeta operador_tarjeta=servicio.eliminar(new OperadorTarjeta(id));
            Respuesta respuesta=new Respuesta(true,"Se elimino una operador de tarjeta", operador_tarjeta);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}