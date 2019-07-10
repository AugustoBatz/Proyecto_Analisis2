/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.inventariomaven;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sys515
 */
@Entity
@Table(name = "Lote")
@NamedQueries({
    @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l"),
    @NamedQuery(name = "Lote.findById", query = "SELECT l FROM Lote l WHERE l.id = :id"),
    @NamedQuery(name = "Lote.findByNoLote", query = "SELECT l FROM Lote l WHERE l.noLote = :noLote"),
    @NamedQuery(name = "Lote.findByCantidad", query = "SELECT l FROM Lote l WHERE l.cantidad = :cantidad"),
    @NamedQuery(name = "Lote.findByCostoUnitario", query = "SELECT l FROM Lote l WHERE l.costoUnitario = :costoUnitario"),
    @NamedQuery(name = "Lote.findByCostoTotal", query = "SELECT l FROM Lote l WHERE l.costoTotal = :costoTotal"),
    @NamedQuery(name = "Lote.findByDisponible", query = "SELECT l FROM Lote l WHERE l.disponible = :disponible"),
    @NamedQuery(name = "Lote.findByGanancia", query = "SELECT l FROM Lote l WHERE l.ganancia = :ganancia"),
    @NamedQuery(name = "Lote.findByPrecioUnitario", query = "SELECT l FROM Lote l WHERE l.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "Lote.findByPrecioTotal", query = "SELECT l FROM Lote l WHERE l.precioTotal = :precioTotal")})
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "NoLote")
    private Integer noLote;
    @Column(name = "Cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CostoUnitario")
    private Float costoUnitario;
    @Column(name = "CostoTotal")
    private Float costoTotal;
    @Column(name = "Disponible")
    private Boolean disponible;
    @Column(name = "Ganancia")
    private Float ganancia;
    @Column(name = "PrecioUnitario")
    private Float precioUnitario;
    @Column(name = "PrecioTotal")
    private Float precioTotal;
    @JoinColumn(name = "FacturaCompra_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FacturaCompra facturaCompraid;
    @JoinColumn(name = "Producto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Producto productoid;

    public Lote() {
    }

    public Lote(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoLote() {
        return noLote;
    }

    public void setNoLote(Integer noLote) {
        this.noLote = noLote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Float getGanancia() {
        return ganancia;
    }

    public void setGanancia(Float ganancia) {
        this.ganancia = ganancia;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public FacturaCompra getFacturaCompraid() {
        return facturaCompraid;
    }

    public void setFacturaCompraid(FacturaCompra facturaCompraid) {
        this.facturaCompraid = facturaCompraid;
    }

    public Producto getProductoid() {
        return productoid;
    }

    public void setProductoid(Producto productoid) {
        this.productoid = productoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.inventariomaven.Lote[ id=" + id + " ]";
    }
    
}
