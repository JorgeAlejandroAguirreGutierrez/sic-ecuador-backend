package com.proyecto.sicecuador.servicios.impl.inventario;

import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.inventario.TipoGasto;
import com.proyecto.sicecuador.modelos.inventario.TipoProducto;
import com.proyecto.sicecuador.repositorios.interf.inventario.ITipoGastoRepository;
import com.proyecto.sicecuador.repositorios.interf.inventario.ITipoProductoRepository;
import com.proyecto.sicecuador.servicios.interf.inventario.ITipoGastoService;
import com.proyecto.sicecuador.servicios.interf.inventario.ITipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoProductoService implements ITipoProductoService {
    @Autowired
    private ITipoProductoRepository rep;
    @Override
    public TipoProducto crear(TipoProducto tipo_producto) {
        return rep.save(tipo_producto);
    }

    @Override
    public TipoProducto actualizar(TipoProducto tipo_producto) {
        return rep.save(tipo_producto);
    }

    @Override
    public TipoProducto eliminar(TipoProducto tipo_producto) {
        rep.deleteById(tipo_producto.getId());
        return tipo_producto;
    }

    @Override
    public Optional<TipoProducto> obtener(TipoProducto tipo_producto) {
        return rep.findById(tipo_producto.getId());
    }

    @Override
    public List<TipoProducto> consultar() {
        return rep.findAll();
    }

    @Override
    public boolean importar(MultipartFile archivo_temporal) {
        try {
            List<TipoProducto> tipos_productos=new ArrayList<>();
            List<List<String>>info= Constantes.leer_importar(archivo_temporal,10);
            for (List<String> datos: info) {
                TipoProducto tipo_producto = new TipoProducto(datos);
                tipos_productos.add(tipo_producto);

            }
            if(tipos_productos.isEmpty()){
                return false;
            }
            rep.saveAll(tipos_productos);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
