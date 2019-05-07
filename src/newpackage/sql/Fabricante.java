/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "FABRICANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fabricante.findAll", query = "SELECT f FROM Fabricante f")
    , @NamedQuery(name = "Fabricante.findByIdFabricante", query = "SELECT f FROM Fabricante f WHERE f.idFabricante = :idFabricante")
    , @NamedQuery(name = "Fabricante.findByNombre", query = "SELECT f FROM Fabricante f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "Fabricante.findByFechaCreacion", query = "SELECT f FROM Fabricante f WHERE f.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Fabricante.findByFoto", query = "SELECT f FROM Fabricante f WHERE f.foto = :foto")
    , @NamedQuery(name = "Fabricante.findByHistoria", query = "SELECT f FROM Fabricante f WHERE f.historia = :historia")})
public class Fabricante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FABRICANTE")
    private Integer idFabricante;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "FOTO")
    private String foto;
    @Column(name = "HISTORIA")
    private String historia;
    @OneToMany(mappedBy = "fabricante")
    private Collection<Guitarra> guitarraCollection;

    public Fabricante() {
    }

    public Fabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public Fabricante(Integer idFabricante, String nombre) {
        this.idFabricante = idFabricante;
        this.nombre = nombre;
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    @XmlTransient
    public Collection<Guitarra> getGuitarraCollection() {
        return guitarraCollection;
    }

    public void setGuitarraCollection(Collection<Guitarra> guitarraCollection) {
        this.guitarraCollection = guitarraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFabricante != null ? idFabricante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fabricante)) {
            return false;
        }
        Fabricante other = (Fabricante) object;
        if ((this.idFabricante == null && other.idFabricante != null) || (this.idFabricante != null && !this.idFabricante.equals(other.idFabricante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.sql.Fabricante[ idFabricante=" + idFabricante + " ]";
    }
    
}
