package com.proyecto.sicecuador.modelos.recaudacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyecto.sicecuador.controladoras.Constantes;
import com.proyecto.sicecuador.modelos.Entidad;
import com.proyecto.sicecuador.modelos.compra.RetencionCompra;
import com.proyecto.sicecuador.modelos.comprobante.Factura;
import com.proyecto.sicecuador.modelos.comprobante.TipoComprobante;
import com.proyecto.sicecuador.modelos.usuario.Sesion;
import com.proyecto.sicecuador.otros.recaudacion.RecaudacionUtil;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recaudacion")
@EntityListeners({RecaudacionUtil.class})
public class Recaudacion extends Entidad {
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha", nullable = true)
    private Date fecha;
    @Column(name = "total", nullable = true)
    private double total;
    @Column(name = "comentario", nullable = true)
    private String comentario;
    @Column(name = "efectivo", nullable = true)
    private double efectivo;
    @Column(name = "total_cheques", nullable = true)
    private double total_cheques;
    @Column(name = "total_depositos", nullable = true)
    private double total_depositos;
    @Column(name = "total_transferencias", nullable = true)
    private double total_transferencias;
    @Column(name = "total_tarjetas_debitos", nullable = true)
    private double total_tarjetas_debitos;
    @Column(name = "total_tarjetas_creditos", nullable = true)
    private double total_tarjetas_creditos;
    @Column(name = "total_compensaciones", nullable = true)
    private double total_compensaciones;
    @Column(name = "total_retenciones_ventas", nullable = true)
    private double total_retenciones_ventas;
    @Column(name = "total_credito", nullable = true)
    private double total_credito;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "credito_id", nullable = true)
    private Credito credito;
    @NotNull(message = "Factura"+ Constantes.mensaje_validacion_not_null)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "factura_id", nullable = true)
    private Factura factura;
    @NotNull(message = "Sesion"+ Constantes.mensaje_validacion_not_null)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sesion_id", nullable = true)
    private Sesion sesion;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cheque_id")
    private List<Cheque> cheques;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "deposito_id")
    private List<Deposito> depositos;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "transferencia_id")
    private List<Transferencia> transferencias;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_credito_id")
    private List<TarjetaCredito> tarjetas_creditos;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_debito_id")
    private List<TarjetaDebito> tarjetas_debitos;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "compensacion_id")
    private List<Compensacion> compensaciones;
    @OneToMany(cascade =CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "retencion_venta_id")
    private List<RetencionVenta> retenciones_ventas;

    public Recaudacion(){
    }

    public Recaudacion(long id){
        super(id);
    }

    public Recaudacion(String codigo, Date fecha, double total, String comentario, double efectivo,
                       double total_cheques, double total_depositos, double total_transferencias,
                       double total_tarjetas_debitos, double total_tarjetas_creditos, double total_credito,
                       double total_compensaciones, double total_retenciones_ventas, List<Cheque> cheques,
                       List<Deposito>depositos, List<Transferencia> transferencias, List<Compensacion> compensaciones, List<RetencionVenta> retenciones_ventas,
                       List<TarjetaCredito> tarjetas_creditos, List<TarjetaDebito> tarjetas_debitos, Credito credito,
                       Factura factura, Sesion sesion){
        super(codigo);
        this.fecha=fecha;
        this.total=total;
        this.comentario=comentario;
        this.efectivo=efectivo;
        this.total_cheques=total_cheques;
        this.total_depositos=total_depositos;
        this.total_transferencias=total_transferencias;
        this.total_tarjetas_debitos=total_tarjetas_debitos;
        this.total_tarjetas_creditos=total_tarjetas_creditos;
        this.total_compensaciones=total_compensaciones;
        this.total_retenciones_ventas=total_retenciones_ventas;
        this.total_credito=total_credito;

        this.cheques=cheques;
        this.depositos=depositos;
        this.transferencias=transferencias;
        this.tarjetas_creditos=tarjetas_creditos;
        this.tarjetas_debitos=tarjetas_debitos;
        this.compensaciones=compensaciones;
        this.retenciones_ventas=retenciones_ventas;
        this.credito=credito;
        this.factura=factura;
        this.sesion=sesion;
    }
    public Date getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public String getComentario() {
        return comentario;
    }

    public double getEfectivo() {
        return efectivo;
    }

    public double getTotal_cheques() {
        return total_cheques;
    }

    public double getTotal_depositos() {
        return total_depositos;
    }

    public double getTotal_transferencias() {
        return total_transferencias;
    }

    public double getTotal_tarjetas_creditos() {
        return total_tarjetas_creditos;
    }

    public double getTotal_tarjetas_debitos() {
        return total_tarjetas_debitos;
    }

    public double getTotal_compensaciones() {
        return total_compensaciones;
    }

    public double getTotal_retenciones_ventas() {
        return total_retenciones_ventas;
    }

    public double getTotal_credito() {
        return total_credito;
    }

    @JsonManagedReference
    public List<Cheque> getCheques() {
        return cheques;
    }
    @JsonManagedReference
    public List<Deposito> getDepositos() {
        return depositos;
    }
    @JsonManagedReference
    public List<Transferencia> getTransferencias() {
        return transferencias;
    }
    @JsonManagedReference
    public List<TarjetaDebito> getTarjetas_debitos() {
        return tarjetas_debitos;
    }
    @JsonManagedReference
    public List<TarjetaCredito> getTarjetas_creditos() {
        return tarjetas_creditos;
    }
    @JsonManagedReference
    public List<Compensacion> getCompensaciones() {
        return compensaciones;
    }
    @JsonManagedReference
    public List<RetencionVenta> getRetenciones_ventas() {
        return retenciones_ventas;
    }

    public Credito getCredito() {
        return credito;
    }

    public Factura getFactura() {
        return factura;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void normalizar(){
        if (this.credito.getSaldo()==0){
            this.credito=null;
        }
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
