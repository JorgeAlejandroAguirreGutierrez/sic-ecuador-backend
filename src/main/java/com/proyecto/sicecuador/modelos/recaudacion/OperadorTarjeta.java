package com.proyecto.sicecuador.modelos.recaudacion;

import com.proyecto.sicecuador.modelos.Entidad;
import com.proyecto.sicecuador.otros.inventario.ProveedorUtil;
import com.proyecto.sicecuador.otros.recaudacion.OperadorTarjetaUtil;

import javax.persistence.*;

@Entity
@Table(name = "operador_tarjeta")
@EntityListeners({OperadorTarjetaUtil.class})
public class OperadorTarjeta extends Entidad {
    @Column(name = "tipo", nullable = true)
    private String tipo;
    @Column(name = "nombre", nullable = true)
    private String nombre;
    @Column(name = "abreviatura", nullable = true)
    private String abreviatura;

    public OperadorTarjeta(){
    }

    public OperadorTarjeta(long id){
        super(id);
    }

    public OperadorTarjeta(String codigo, String tipo, String nombre, String abreviatura){
        super(codigo);
        this.tipo=tipo;
        this.nombre=nombre;
        this.abreviatura=abreviatura;
    }
    public OperadorTarjeta(String tipo){
        super();
        this.tipo=tipo;
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
