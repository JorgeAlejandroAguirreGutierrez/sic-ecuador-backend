package com.proyecto.sicecuador.servicios.impl.inventario;

import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.exception.ModeloExistenteException;
import com.proyecto.sicecuador.exception.ModeloNoExistenteException;
import com.proyecto.sicecuador.modelos.inventario.TablaEquivalenciaMedida;
import com.proyecto.sicecuador.otros.Util;
import com.proyecto.sicecuador.repositorios.interf.inventario.ITablaEquivalenciaMedidaRepository;
import com.proyecto.sicecuador.servicios.interf.inventario.ITablaEquivalenciaMedidaService;
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
public class TablaEquivalenciaMedidaService implements ITablaEquivalenciaMedidaService {
    @Autowired
    private ITablaEquivalenciaMedidaRepository rep;
    @Override
    public TablaEquivalenciaMedida crear(TablaEquivalenciaMedida tabla) {
    	Optional<TablaEquivalenciaMedida> tem= this.obtenerMedida1Medida2(tabla);
    	if(tem.isPresent()) {
    		throw new ModeloExistenteException();
    	}
        return rep.save(tabla);
    }

    @Override
    public TablaEquivalenciaMedida actualizar(TablaEquivalenciaMedida tabla) {
    	Optional<TablaEquivalenciaMedida> optionalTem= this.obtenerMedida1Medida2(tabla);
    	if(optionalTem.isPresent()) {
    		TablaEquivalenciaMedida tem=optionalTem.get();
    		tem.setEquivalencia(tabla.getEquivalencia());
    		return rep.save(tem);
    	}
    	throw new ModeloNoExistenteException();
    }

    @Override
    public TablaEquivalenciaMedida eliminar(TablaEquivalenciaMedida tabla) {
        rep.deleteById(tabla.getId());
        return tabla;
    }

    @Override
    public Optional<TablaEquivalenciaMedida> obtener(TablaEquivalenciaMedida tabla) {
        return rep.findById(tabla.getId());
    }

    @Override
    public List<TablaEquivalenciaMedida> consultar() {
        return rep.findAll();
    }

    @Override
    public Optional<TablaEquivalenciaMedida> obtenerMedida1Medida2(TablaEquivalenciaMedida _tabla){
        Optional<TablaEquivalenciaMedida> tabla =  rep.findOne(new Specification<TablaEquivalenciaMedida>() {
            @Override
            public Predicate toPredicate(Root<TablaEquivalenciaMedida> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(_tabla.getMedida1() != null){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("medida1").get("id"), _tabla.getMedida1().getId())));
                }
                if(_tabla.getMedida2() != null){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("medida2").get("id"), _tabla.getMedida2().getId())));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
        return tabla;
    }
    
    @Override
    public List<TablaEquivalenciaMedida> buscar(TablaEquivalenciaMedida tem) {
        return  rep.findAll(new Specification<TablaEquivalenciaMedida>() {
            @Override
            public Predicate toPredicate(Root<TablaEquivalenciaMedida> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!tem.getCodigo().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("codigo").get("codigo"), "%"+tem.getCodigo()+"%")));
                }
                if (!tem.getMedida1().getDescripcion().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("medida1").get("descripcion"), "%"+tem.getMedida1().getDescripcion()+"%")));
                }
                if (!tem.getMedida2().getDescripcion().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("medida2").get("descripcion"), "%"+tem.getMedida2().getDescripcion()+"%")));
                }
                if (tem.getEquivalencia()!=0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("equivalencia"), "%"+tem.getEquivalencia()+"%")));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public boolean importar(MultipartFile archivo_temporal) {
        try {
            List<TablaEquivalenciaMedida> tablas=new ArrayList<>();
            List<List<String>>info= Constantes.leer_importar(archivo_temporal,0);
            for (List<String> datos: info) {
                TablaEquivalenciaMedida tabla = new TablaEquivalenciaMedida(datos);
                tablas.add(tabla);
            }
            if(tablas.isEmpty()){
                return false;
            }
            rep.saveAll(tablas);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
