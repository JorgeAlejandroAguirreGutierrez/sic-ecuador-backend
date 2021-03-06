package com.proyecto.sicecuador.modelos.cliente;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.sicecuador.modelos.Entidad;
import com.proyecto.sicecuador.modelos.configuracion.TipoRetencion;
import com.proyecto.sicecuador.otros.cliente.RetencionClienteUtil;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "retencion_cliente")
//@EntityListeners({RetencionClienteUtil.class})
public class RetencionCliente extends Entidad {
    @ManyToOne
    @JsonProperty("tipo_retencion")
    @JoinColumn(name = "tipo_retencion_id", nullable = true)
    private TipoRetencion tipoRetencion;
    @ManyToOne
    @JsonProperty("cliente")
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

    public RetencionCliente(){
        super();
    }

    public RetencionCliente(long id) {
        super(id);
    }

    public RetencionCliente(String codigo, TipoRetencion tipoRetencion, Cliente cliente){
        super(codigo);
        this.tipoRetencion=tipoRetencion;
        this.cliente=cliente;
    }

    public RetencionCliente(List<String> datos){
        tipoRetencion=datos.get(0)== null ? null:new TipoRetencion((long) Double.parseDouble(datos.get(0)));
        cliente=datos.get(1)== null ? null:new Cliente((long) Double.parseDouble(datos.get(1)));
    }

    public TipoRetencion getTipoRetencion() {
		return tipoRetencion;
	}
    
    public void setTipoRetencion(TipoRetencion tipoRetencion) {
		this.tipoRetencion = tipoRetencion;
	}

    @JsonBackReference
    public Cliente getCliente() {
        return cliente;
    }
}
