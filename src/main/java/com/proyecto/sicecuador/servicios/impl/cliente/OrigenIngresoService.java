package com.proyecto.sicecuador.servicios.impl.cliente;

import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.cliente.FormaPago;
import com.proyecto.sicecuador.modelos.cliente.Genero;
import com.proyecto.sicecuador.modelos.cliente.OrigenIngreso;
import com.proyecto.sicecuador.otros.Util;
import com.proyecto.sicecuador.repositorios.interf.cliente.IOrigenIngresoRepository;
import com.proyecto.sicecuador.servicios.interf.cliente.IOrigenIngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Service
public class OrigenIngresoService implements IOrigenIngresoService {
    @Autowired
    private IOrigenIngresoRepository rep;
    @Override
    public OrigenIngreso crear(OrigenIngreso origen_ingreso) {
        return rep.save(origen_ingreso);
    }

    @Override
    public OrigenIngreso actualizar(OrigenIngreso origen_ingreso) {
        return rep.save(origen_ingreso);
    }

    @Override
    public OrigenIngreso eliminar(OrigenIngreso origen_ingreso) {
        rep.deleteById(origen_ingreso.getId());
        return origen_ingreso;
    }

    @Override
    public Optional<OrigenIngreso> obtener(OrigenIngreso origen_ingreso) {
        return rep.findById(origen_ingreso.getId());
    }

    @Override
    public List<OrigenIngreso> consultar() {
        return rep.findAll();
    }
    
    @Override
    public List<OrigenIngreso> buscar(OrigenIngreso origen_ingreso) {
        return  rep.findAll(new Specification<OrigenIngreso>() {
            @Override
            public Predicate toPredicate(Root<OrigenIngreso> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!origen_ingreso.getCodigo().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("codigo"), "%"+origen_ingreso.getCodigo()+"%")));
                }
                if (!origen_ingreso.getDescripcion().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("descripcion"), "%"+origen_ingreso.getDescripcion()+"%")));
                }
                if (!origen_ingreso.getAbreviatura().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("abreviatura"), "%"+origen_ingreso.getAbreviatura()+"%")));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public boolean importar(MultipartFile archivo_temporal) {
        try {
            List<OrigenIngreso> origenes_ingresos=new ArrayList<>();
            List<List<String>>info= Constantes.leer_importar(archivo_temporal, 13);
            for (List<String> datos: info) {
                OrigenIngreso origen_ingreso = new OrigenIngreso(datos);
                origenes_ingresos.add(origen_ingreso);
            }
            if(origenes_ingresos.isEmpty()){
                return false;
            }
            rep.saveAll(origenes_ingresos);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
