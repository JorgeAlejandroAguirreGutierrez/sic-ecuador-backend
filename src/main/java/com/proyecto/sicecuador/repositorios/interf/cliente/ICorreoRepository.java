package com.proyecto.sicecuador.repositorios.interf.cliente;

import com.proyecto.sicecuador.modelos.cliente.Correo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICorreoRepository extends JpaRepository<Correo, Long>, JpaSpecificationExecutor<Correo> {
}
