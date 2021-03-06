package com.proyecto.sicecuador.servicios.impl.cliente;

import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.cliente.Cliente;
import com.proyecto.sicecuador.modelos.cliente.RetencionCliente;
import com.proyecto.sicecuador.modelos.cliente.Telefono;
import com.proyecto.sicecuador.repositorios.interf.cliente.IClienteRepository;
import com.proyecto.sicecuador.repositorios.interf.cliente.IOrigenIngresoRepository;
import com.proyecto.sicecuador.repositorios.interf.cliente.ITelefonoRepository;
import com.proyecto.sicecuador.servicios.interf.cliente.ITelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TelefonoService implements ITelefonoService {
    @Autowired
    private ITelefonoRepository rep;
    @Autowired
    private IClienteRepository rep_cliente;
    @Override
    public Telefono crear(Telefono telefono) {
        return rep.save(telefono);
    }

    @Override
    public Telefono actualizar(Telefono telefono) {
        return rep.save(telefono);
    }

    @Override
    public Telefono eliminar(Telefono telefono) {
        rep.deleteById(telefono.getId());
        return telefono;
    }

    @Override
    public Optional<Telefono> obtener(Telefono telefono) {
        return rep.findById(telefono.getId());
    }

    @Override
    public List<Telefono> consultar() {
        return rep.findAll();
    }

    @Override
    public boolean importar(MultipartFile archivo_temporal) {
        try {
            List<Telefono> telefonos=new ArrayList<>();
            List<List<String>>info= Constantes.leer_importar(archivo_temporal, 16);
            for (List<String> datos: info) {
                Telefono telefono = new Telefono(datos);
                Optional<Cliente> cliente=rep_cliente.findById(telefono.getCliente().getId());
                if(cliente.isPresent()){
                    telefonos.add(telefono);
                }
            }
            if(telefonos.isEmpty()){
                return false;
            }
            rep.saveAll(telefonos);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
