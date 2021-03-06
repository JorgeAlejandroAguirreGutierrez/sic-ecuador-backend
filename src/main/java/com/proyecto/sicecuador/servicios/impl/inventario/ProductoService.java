package com.proyecto.sicecuador.servicios.impl.inventario;

import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.cliente.CategoriaCliente;
import com.proyecto.sicecuador.modelos.comprobante.Factura;
import com.proyecto.sicecuador.modelos.inventario.Bodega;
import com.proyecto.sicecuador.modelos.inventario.Medida;
import com.proyecto.sicecuador.modelos.inventario.Producto;
import com.proyecto.sicecuador.otros.Util;
import com.proyecto.sicecuador.repositorios.interf.inventario.IProductoRepository;
import com.proyecto.sicecuador.servicios.interf.inventario.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepository rep;
    @Override
    public Producto crear(Producto producto) {
        return rep.save(producto);
    }

    @Override
    public Producto actualizar(Producto producto) {
        return rep.save(producto);
    }

    @Override
    public Producto eliminar(Producto producto) {
        rep.deleteById(producto.getId());
        return producto;
    }

    @Override
    public Optional<Producto> obtener(Producto producto) {
        return rep.findById(producto.getId());
    }

    @Override
    public List<Producto> consultar() {
        return rep.findAll();
    }

    @Override
    public List<Producto> consultarBien() {
        List<Producto> productos=  rep.findAll(new Specification<Producto>() {
            @Override
            public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tipo_producto").get("tipo"), "BIEN")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
        return productos;
    }

    @Override
    public List<Producto> consultarServicio() {
        return  rep.findAll(new Specification<Producto>() {
            @Override
            public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tipoProducto").get("tipo"), "SERVICIO")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public List<Producto> consultarActivoFijo() {
        return  rep.findAll(new Specification<Producto>() {
            @Override
            public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tipoProducto").get("tipo"), "ACTIVOFIJO")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
    @Override
    public List<Producto> consultarBodega() {
        return  rep.findAll(new Specification<Producto>() {
            @Override
            public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tipoProducto").get("tipo"), "ACTIVOFIJO")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public List<Producto> buscar(Producto producto) {
        return  rep.findAll(new Specification<Producto>() {
            @Override
            public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!producto.getNombre().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nombre"), "%"+producto.getNombre()+"%")));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
    
    @Override
    public boolean importar(MultipartFile archivo_temporal) {
        try {
            List<Producto> productos=new ArrayList<>();
            List<List<String>>info= Constantes.leer_importar(archivo_temporal,7);
            for (List<String> datos: info) {
                Producto producto = new Producto(datos);
                productos.add(producto);
            }
            if(productos.isEmpty()){
                return false;
            }
            rep.saveAll(productos);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
