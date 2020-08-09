package com.proyecto.sicecuador.modelos.cliente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.Entidad;
import com.proyecto.sicecuador.modelos.usuario.PuntoVenta;
import com.proyecto.sicecuador.otros.cliente.AuxiliarUtil;
import com.proyecto.sicecuador.otros.cliente.ClienteUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "auxiliar")
@EntityListeners({AuxiliarUtil.class})
public class Auxiliar extends Entidad {
    @NotNull(message = "Razon Social"+ Constantes.mensaje_validacion_not_null)
    @NotBlank(message = "Razon Social"+Constantes.mensaje_validacion_not_blank)
    @Column(name = "razon_social", nullable = true)
    private String razon_social;
    @NotNull(message = "Estado"+ Constantes.mensaje_validacion_not_null)
    @Column(name = "estado")
    private boolean estado;
    @NotNull(message = "Eliminado"+ Constantes.mensaje_validacion_not_null)
    @Column(name = "eliminado")
    private boolean eliminado;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE},fetch= FetchType.LAZY)
    @JoinColumn(name = "direccion_id", nullable = true)
    private Direccion direccion;
    @NotNull(message = "Cliente"+ Constantes.mensaje_validacion_not_null)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "auxiliar_id")
    private List<TelefonoAuxiliar> telefonos;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "auxiliar_id")
    private List<CelularAuxiliar> celulares;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "auxiliar_id")
    private List<CorreoAuxiliar> correos;

    public Auxiliar(){

    }

    public Auxiliar(long id) {
        super(id);
    }

    public Auxiliar(String codigo, String razon_social, boolean estado, boolean eliminado, Cliente cliente, Direccion direccion){
        super(codigo);
        this.razon_social=razon_social;
        this.estado=estado;
        this.eliminado=eliminado;
        this.direccion=direccion;
        this.cliente=cliente;

    }

    public Auxiliar(String razon_social, Cliente cliente){
        super("");
        this.razon_social=razon_social;
        this.cliente=cliente;
    }

    public Auxiliar(Cliente cliente){
        super("");
        this.cliente=cliente;
    }

    public Auxiliar(List<String>datos){
        this.razon_social=datos.get(0)== null? null : datos.get(0);
        this.estado=datos.get(1)== null ? null: datos.get(1).equals("S") ? true : false;
        this.eliminado=datos.get(2)== null ? null: datos.get(2).equals("S") ? true : false;
        this.direccion=datos.get(3)==null? null: new Direccion((long) Double.parseDouble(datos.get(3)));
        this.cliente=datos.get(4)== null ? null: new Cliente((long) Double.parseDouble(datos.get(4)));
    }

    public String getRazon_social() {
        return razon_social;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public boolean isEstado() {
        return estado;
    }
    @JsonBackReference
    public Cliente getCliente() {
        return cliente;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    @JsonManagedReference
    public List<TelefonoAuxiliar> getTelefonos() {
        return telefonos;
    }
    @JsonManagedReference
    public List<CelularAuxiliar> getCelulares() {
        return celulares;
    }
    @JsonManagedReference
    public List<CorreoAuxiliar> getCorreos() {
        return correos;
    }
}
