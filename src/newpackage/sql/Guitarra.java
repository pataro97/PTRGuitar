/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "GUITARRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guitarra.findAll", query = "SELECT g FROM Guitarra g")
    , @NamedQuery(name = "Guitarra.findByIdGuitarra", query = "SELECT g FROM Guitarra g WHERE g.idGuitarra = :idGuitarra")
    , @NamedQuery(name = "Guitarra.findByA\u00f1o", query = "SELECT g FROM Guitarra g WHERE g.a\u00f1o = :a\u00f1o")
    , @NamedQuery(name = "Guitarra.findByModelo", query = "SELECT g FROM Guitarra g WHERE g.modelo = :modelo")
    , @NamedQuery(name = "Guitarra.findByFoto", query = "SELECT g FROM Guitarra g WHERE g.foto = :foto")
    , @NamedQuery(name = "Guitarra.findByStock", query = "SELECT g FROM Guitarra g WHERE g.stock = :stock")
    , @NamedQuery(name = "Guitarra.findByPrecio", query = "SELECT g FROM Guitarra g WHERE g.precio = :precio")
    , @NamedQuery(name = "Guitarra.findByMadera", query = "SELECT g FROM Guitarra g WHERE g.madera = :madera")
    , @NamedQuery(name = "Guitarra.findByTipo", query = "SELECT g FROM Guitarra g WHERE g.tipo = :tipo")})
public class Guitarra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GUITARRA")
    private Integer idGuitarra;
    @Column(name = "A\u00d1O")
    @Temporal(TemporalType.DATE)
    private Date año;
    @Basic(optional = false)
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "FOTO")
    private String foto;
    @Column(name = "STOCK")
    private Boolean stock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Column(name = "MADERA")
    private String madera;
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "FABRICANTE", referencedColumnName = "ID_FABRICANTE")
    @ManyToOne
    private Fabricante fabricante;

    public Guitarra() {
    }

    public Guitarra(Integer idGuitarra) {
        this.idGuitarra = idGuitarra;
    }

    public Guitarra(Integer idGuitarra, String modelo) {
        this.idGuitarra = idGuitarra;
        this.modelo = modelo;
    }

    public Integer getIdGuitarra() {
        return idGuitarra;
    }

    public void setIdGuitarra(Integer idGuitarra) {
        this.idGuitarra = idGuitarra;
    }

    public Date getAño() {
        return año;
    }

    public void setAño(Date año) {
        this.año = año;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getMadera() {
        return madera;
    }

    public void setMadera(String madera) {
        this.madera = madera;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGuitarra != null ? idGuitarra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guitarra)) {
            return false;
        }
        Guitarra other = (Guitarra) object;
        if ((this.idGuitarra == null && other.idGuitarra != null) || (this.idGuitarra != null && !this.idGuitarra.equals(other.idGuitarra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.sql.Guitarra[ idGuitarra=" + idGuitarra + " ]";
    }
    
}
