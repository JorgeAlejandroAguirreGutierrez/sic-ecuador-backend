package com.proyecto.sicecuador.modelos.recaudacion;

import com.proyecto.sicecuador.modelos.Entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "franquicia_tarjeta")
public class FranquiciaTarjeta extends Entidad {
    @Column(name = "tipo", nullable = true)
    private String tipo;
    @Column(name = "nombre", nullable = true)
    private String nombre;
    @Column(name = "abreviatura", nullable = true)
    private String abreviatura;

    public FranquiciaTarjeta(){
        super();
    }

    public FranquiciaTarjeta(long id){
        super(id);
    }

    public FranquiciaTarjeta(String codigo, String tipo, String nombre, String abreviatura){
        super(codigo);
        this.tipo=tipo;
        this.nombre=nombre;
        this.abreviatura=abreviatura;
    }
    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }
}