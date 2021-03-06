package com.proyecto.sicecuador.modelos.recaudacion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.sicecuador.modelos.Entidad;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transferencia")
public class Transferencia extends Entidad {
	@JsonProperty("tipo_transaccion")
    @Column(name = "tipo_transaccion", nullable = true)
    private String tipoTransaccion;
	@JsonProperty("numero_transaccion")
    @Column(name = "numero_transaccion", nullable = true)
    private String numeroTransaccion;
	@JsonProperty("fecha_transaccion")
    @Column(name = "fecha_transaccion", nullable = true)
    private Date fechaTransaccion;
	@JsonProperty("valor")
    @Column(name = "valor", nullable = true)
    private double valor;
    @ManyToOne
    @JsonProperty("banco")
    @JoinColumn(name = "banco_id", nullable = true)
    private Banco banco;
    @ManyToOne
    @JsonProperty("recaudacion")
    @JoinColumn(name = "recaudacion_id", nullable = true)
    private Recaudacion recaudacion;

    public Transferencia(){
    }

    public Transferencia(long id){
        super(id);
    }

    public Transferencia(String codigo, String tipoTransaccion, String numeroTransaccion, Date fechaTransaccion, double valor, Recaudacion recaudacion, Banco banco){
        super(codigo);
        this.tipoTransaccion=tipoTransaccion;
        this.numeroTransaccion=numeroTransaccion;
        this.fechaTransaccion=fechaTransaccion;
        this.valor=valor;
        this.recaudacion=recaudacion;
        this.banco=banco;
    }
    
    public String getTipoTransaccion() {
		return tipoTransaccion;
	}

    public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

    public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

    public double getValor() {
        return valor;
    }

    public Banco getBanco() {
        return banco;
    }
    @JsonBackReference
    public Recaudacion getRecaudacion() {
        return recaudacion;
    }
}
