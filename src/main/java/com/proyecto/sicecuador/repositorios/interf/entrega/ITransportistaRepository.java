package com.proyecto.sicecuador.repositorios.interf.entrega;

import com.proyecto.sicecuador.modelos.entrega.Transportista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransportistaRepository extends JpaRepository<Transportista, Long>, JpaSpecificationExecutor<Transportista> {
}
