package com.proyecto.sicecuador.controladoras.cliente;

import static com.proyecto.sicecuador.controladoras.Endpoints.contexto;
import static com.proyecto.sicecuador.controladoras.Endpoints.pathRetencionCliente;
import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.controladoras.GenericoController;
import com.proyecto.sicecuador.modelos.Respuesta;
import com.proyecto.sicecuador.modelos.cliente.RetencionCliente;
import com.proyecto.sicecuador.servicios.interf.cliente.IRetencionClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(contexto+pathRetencionCliente)
public class RetencionClienteController implements GenericoController<RetencionCliente> {
    @Autowired
    private IRetencionClienteService servicio;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultar() {
        try {
            List<RetencionCliente> retenciones_clientes = servicio.consultar();
            Respuesta respuesta = new Respuesta(true, Constantes.mensaje_consultar_exitoso, retenciones_clientes);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable("id") long id) {
        try {
            RetencionCliente retencion_cliente=servicio.obtener(new RetencionCliente(id)).get();
            Respuesta respuesta=new Respuesta(true,Constantes.mensaje_obtener_exitoso, retencion_cliente);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch(Exception e){
            Respuesta respuesta = new Respuesta(false, e.getMessage(), null);
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody @Valid RetencionCliente _retencionCliente) {
        RetencionCliente retencion_cliente=servicio.crear(_retencionCliente);
        Respuesta respuesta=new Respuesta(true,Constantes.mensaje_crear_exitoso, retencion_cliente);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@RequestBody RetencionCliente _retencionCliente) {
        RetencionCliente retencion_cliente=servicio.actualizar(_retencionCliente);
        Respuesta respuesta=new Respuesta(true,Constantes.mensaje_actualizar_exitoso, retencion_cliente);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable("id") long id)  {
        RetencionCliente retencion_cliente=servicio.eliminar(new RetencionCliente(id));
        Respuesta respuesta=new Respuesta(true,Constantes.mensaje_eliminar_exitoso, retencion_cliente);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping(value = "/importar", headers = "content-type=multipart/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> importar(@RequestPart("archivo") MultipartFile archivo) {
        boolean bandera=servicio.importar(archivo);
        Respuesta respuesta=new Respuesta(true,Constantes.mensaje_crear_exitoso, bandera);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
