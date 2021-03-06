package com.proyecto.sicecuador.servicios.impl.cliente;

import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.cliente.Auxiliar;
import com.proyecto.sicecuador.modelos.cliente.CategoriaCliente;
import com.proyecto.sicecuador.modelos.cliente.Cliente;
import com.proyecto.sicecuador.modelos.cliente.Direccion;
import com.proyecto.sicecuador.modelos.cliente.GrupoCliente;
import com.proyecto.sicecuador.otros.Util;
import com.proyecto.sicecuador.repositorios.interf.cliente.ICategoriaClienteRepository;
import com.proyecto.sicecuador.servicios.interf.cliente.ICategoriaClienteService;
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
public class CategoriaClienteService implements ICategoriaClienteService {
    @Autowired
    private ICategoriaClienteRepository rep;
    @Override
    public CategoriaCliente crear(CategoriaCliente categoria_cliente) {
        return rep.save(categoria_cliente);
    }

    @Override
    public CategoriaCliente actualizar(CategoriaCliente categoria_cliente) {
        return rep.save(categoria_cliente);
    }

    @Override
    public CategoriaCliente eliminar(CategoriaCliente categoria_cliente) {
        rep.deleteById(categoria_cliente.getId());
        return categoria_cliente;
    }

    @Override
    public Optional<CategoriaCliente> obtener(CategoriaCliente categoria_cliente) {
        return rep.findById(categoria_cliente.getId());
    }

    @Override
    public List<CategoriaCliente> consultar() {
        return rep.findAll();
    }
    
    @Override
    public List<CategoriaCliente> buscar(CategoriaCliente categoria_cliente) {
        return  rep.findAll(new Specification<CategoriaCliente>() {
            @Override
            public Predicate toPredicate(Root<CategoriaCliente> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!categoria_cliente.getCodigo().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("codigo"), "%"+categoria_cliente.getCodigo()+"%")));
                }
                if (!categoria_cliente.getDescripcion().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("descripcion"), "%"+categoria_cliente.getDescripcion()+"%")));
                }
                if (!categoria_cliente.getAbreviatura().equals(Util.vacio)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("abreviatura"), "%"+categoria_cliente.getAbreviatura()+"%")));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public boolean importar(MultipartFile archivo_temporal) {
        try {
            List<CategoriaCliente> categorias_clientes=new ArrayList<>();
            List<List<String>>info= Constantes.leer_importar(archivo_temporal,1);
            for (List<String> datos: info) {
                CategoriaCliente categoria_cliente = new CategoriaCliente(datos);
                categorias_clientes.add(categoria_cliente);
            }
            if(categorias_clientes.isEmpty()){
                return false;
            }
            rep.saveAll(categorias_clientes);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
