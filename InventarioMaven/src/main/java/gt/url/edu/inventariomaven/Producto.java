/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.inventariomaven;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author sys515
 */
@Entity
@Table(name = "Producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByExistencia", query = "SELECT p FROM Producto p WHERE p.existencia = :existencia"),
    @NamedQuery(name = "Producto.findByCodigo", query = "SELECT p FROM Producto p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Producto.findByMedida", query = "SELECT p FROM Producto p WHERE p.medida = :medida"),
    @NamedQuery(name = "Producto.findByStockMinimo", query = "SELECT p FROM Producto p WHERE p.stockMinimo = :stockMinimo")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Existencia")
    private Integer existencia;
    @Column(name = "Codigo")
    private String codigo;
    @Column(name = "Medida")
    private String medida;
    @Column(name = "StockMinimo")
    private Integer stockMinimo;
    
    @JoinColumn(name = "Categoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria categoriaid;
    @JoinColumn(name = "Presentacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Presentacion presentacionid;
    @JoinColumn(name = "Unidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Unidad unidadid;
    @JoinColumn(name = "Marca_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Marca marcaid;
    

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    

    public Categoria getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(Categoria categoriaid) {
        this.categoriaid = categoriaid;
    }

    public Presentacion getPresentacionid() {
        return presentacionid;
    }

    public void setPresentacionid(Presentacion presentacionid) {
        this.presentacionid = presentacionid;
    }

    public Unidad getUnidadid() {
        return unidadid;
    }

    public void setUnidadid(Unidad unidadid) {
        this.unidadid = unidadid;
    }

    public Marca getMarcaid() {
        return marcaid;
    }

    public void setMarcaid(Marca marcaid) {
        this.marcaid = marcaid;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id+", "+codigo+", "+nombre+", "+getMarcaid().getMarca()+", "+getPresentacionid().getPresentacion()+", "+getUnidadid().getUnidad();
    }
    
}
