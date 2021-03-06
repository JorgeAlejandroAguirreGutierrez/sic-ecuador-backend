package com.proyecto.sicecuador.servicios.impl.entrega;

import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.entrega.Transportista;
import com.proyecto.sicecuador.modelos.entrega.VehiculoTransporte;
import com.proyecto.sicecuador.repositorios.interf.entrega.IVehiculoTransporteRepository;
import com.proyecto.sicecuador.servicios.interf.entrega.IVehiculoTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class VehiculoTransporteService implements IVehiculoTransporteService {
    @Autowired
    private IVehiculoTransporteRepository rep;
    @Override
    public VehiculoTransporte crear(VehiculoTransporte vehiculo_transporte) {
        return rep.save(vehiculo_transporte);
    }

    @Override
    public VehiculoTransporte actualizar(VehiculoTransporte vehiculo_transporte) {
        return rep.save(vehiculo_transporte);
    }

    @Override
    public VehiculoTransporte eliminar(VehiculoTransporte vehiculo_transporte) {
        rep.deleteById(vehiculo_transporte.getId());
        return vehiculo_transporte;
    }

    @Override
    public Optional<VehiculoTransporte> obtener(VehiculoTransporte vehiculo_transporte) {
        return rep.findById(vehiculo_transporte.getId());
    }

    @Override
    public List<VehiculoTransporte> consultar() {
        return rep.findAll();
    }

    @Override
    public boolean importar(MultipartFile archivo_temporal) {
        try {
            List<VehiculoTransporte> vehiculos_transportes=new ArrayList<>();
            List<List<String>>info= Constantes.leer_importar(archivo_temporal,2);
            for (List<String> datos: info) {
                VehiculoTransporte vehculo_transporte = new VehiculoTransporte(datos);
                vehiculos_transportes.add(vehculo_transporte);
            }
            if (vehiculos_transportes.isEmpty()){
                return false;
            }
            rep.saveAll(vehiculos_transportes);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
