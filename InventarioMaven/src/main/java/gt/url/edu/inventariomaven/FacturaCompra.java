/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.inventariomaven;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sys515
 */
@Entity
@Table(name = "FacturaCompra")
@NamedQueries({
    @NamedQuery(name = "FacturaCompra.findAll", query = "SELECT f FROM FacturaCompra f"),
    @NamedQuery(name = "FacturaCompra.findById", query = "SELECT f FROM FacturaCompra f WHERE f.id = :id"),
    @NamedQuery(name = "FacturaCompra.findBySerie", query = "SELECT f FROM FacturaCompra f WHERE f.serie = :serie"),
    @NamedQuery(name = "FacturaCompra.findByNumero", query = "SELECT f FROM FacturaCompra f WHERE f.numero = :numero"),
    @NamedQuery(name = "FacturaCompra.findByTotal", query = "SELECT f FROM FacturaCompra f WHERE f.total = :total"),
    @NamedQuery(name = "FacturaCompra.findByFecha", query = "SELECT f FROM FacturaCompra f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "FacturaCompra.findByTotalFactura", query = "SELECT f FROM FacturaCompra f WHERE f.totalFactura = :totalFactura"),
    @NamedQuery(name = "FacturaCompra.findByAnulado", query = "SELECT f FROM FacturaCompra f WHERE f.anulado = :anulado"),
    @NamedQuery(name = "FacturaCompra.findByCantidadProd", query = "SELECT f FROM FacturaCompra f WHERE f.cantidadProd = :cantidadProd")})
public class FacturaCompra implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Serie")
    private String serie;
    @Column(name = "Numero")
    private Integer numero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total")
    private Float total;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "Total_Factura")
    private Float totalFactura;
    @Column(name = "Anulado")
    private Boolean anulado;
    @Column(name = "Cantidad_Prod")
    private Integer cantidadProd;
    @JoinColumn(name = "Proveedor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proveedor proveedorid;

    public FacturaCompra() {
    }

    public FacturaCompra(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Float totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Integer getCantidadProd() {
        return cantidadProd;
    }

    public void setCantidadProd(Integer cantidadProd) {
        this.cantidadProd = cantidadProd;
    }

    
    public Proveedor getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(Proveedor proveedorid) {
        this.proveedorid = proveedorid;
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
        if (!(object instanceof FacturaCompra)) {
            return false;
        }
        FacturaCompra other = (FacturaCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

  

    
}
