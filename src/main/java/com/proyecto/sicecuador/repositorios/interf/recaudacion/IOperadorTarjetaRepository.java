package com.proyecto.sicecuador.repositorios.interf.recaudacion;

import com.proyecto.sicecuador.modelos.recaudacion.OperadorTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperadorTarjetaRepository extends JpaRepository<OperadorTarjeta, Long>, JpaSpecificationExecutor<OperadorTarjeta> {
}
