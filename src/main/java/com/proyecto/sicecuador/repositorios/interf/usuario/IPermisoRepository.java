package com.proyecto.sicecuador.repositorios.interf.usuario;

import com.proyecto.sicecuador.modelos.usuario.Permiso;
import com.proyecto.sicecuador.modelos.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisoRepository extends JpaRepository<Permiso, Long>, JpaSpecificationExecutor<Permiso> {
}
