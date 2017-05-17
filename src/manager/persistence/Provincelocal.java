/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "provincelocal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincelocal.findAll", query = "SELECT p FROM Provincelocal p"),
    @NamedQuery(name = "Provincelocal.findByPovinceId", query = "SELECT p FROM Provincelocal p WHERE p.povinceId = :povinceId"),
    @NamedQuery(name = "Provincelocal.findByProvinceName", query = "SELECT p FROM Provincelocal p WHERE p.provinceName = :provinceName"),
    @NamedQuery(name = "Provincelocal.findByX", query = "SELECT p FROM Provincelocal p WHERE p.x = :x"),
    @NamedQuery(name = "Provincelocal.findByY", query = "SELECT p FROM Provincelocal p WHERE p.y = :y")})
public class Provincelocal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "povince_id")
    private Long povinceId;
    @Column(name = "province_name")
    private String provinceName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "x")
    private Double x;
    @Column(name = "y")
    private Double y;
    @OneToMany(mappedBy = "prov1PovinceId")
    private Collection<Provinceneighborslocal> provinceneighborslocalCollection;
    @OneToMany(mappedBy = "prov2PovinceId")
    private Collection<Provinceneighborslocal> provinceneighborslocalCollection1;

    public Provincelocal() {
    }

    public Provincelocal(Long povinceId) {
        this.povinceId = povinceId;
    }

    public Long getPovinceId() {
        return povinceId;
    }

    public void setPovinceId(Long povinceId) {
        this.povinceId = povinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @XmlTransient
    public Collection<Provinceneighborslocal> getProvinceneighborslocalCollection() {
        return provinceneighborslocalCollection;
    }

    public void setProvinceneighborslocalCollection(Collection<Provinceneighborslocal> provinceneighborslocalCollection) {
        this.provinceneighborslocalCollection = provinceneighborslocalCollection;
    }

    @XmlTransient
    public Collection<Provinceneighborslocal> getProvinceneighborslocalCollection1() {
        return provinceneighborslocalCollection1;
    }

    public void setProvinceneighborslocalCollection1(Collection<Provinceneighborslocal> provinceneighborslocalCollection1) {
        this.provinceneighborslocalCollection1 = provinceneighborslocalCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (povinceId != null ? povinceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincelocal)) {
            return false;
        }
        Provincelocal other = (Provincelocal) object;
        if ((this.povinceId == null && other.povinceId != null) || (this.povinceId != null && !this.povinceId.equals(other.povinceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Provincelocal[ povinceId=" + povinceId + " ]";
    }
    
}
