package com.proyecto.sicecuador.modelos.comprobante;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.sicecuador.modelos.Entidad;
import com.proyecto.sicecuador.otros.comprobante.TipoComprobanteUtil;

import javax.persistence.*;

@Entity
@Table(name = "tipo_comprobante")
//@EntityListeners({TipoComprobanteUtil.class})
public class TipoComprobante extends Entidad {
	@JsonProperty("descripcion")
    @Column(name = "descripcion", nullable = true)
    private String descripcion;
	@JsonProperty("nombre")
    @Column(name = "nombre", nullable = true)
    private String nombre;
	@JsonProperty("nombre_tabla")
    @Column(name = "nombre_tabla", nullable = true)
    private String nombreTabla;

    public TipoComprobante(){

    }

    public TipoComprobante(long id){
        super(id);
    }
    public TipoComprobante(String codigo, String nombre, String descripcion, String nombreTabla){
        super(codigo);
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.nombreTabla=nombreTabla;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreTabla() {
		return nombreTabla;
	}

    public String getNombre() {
        return nombre;
    }
}
