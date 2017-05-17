/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "provinceneighborslocal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provinceneighborslocal.findAll", query = "SELECT p FROM Provinceneighborslocal p"),
    @NamedQuery(name = "Provinceneighborslocal.findById", query = "SELECT p FROM Provinceneighborslocal p WHERE p.id = :id")})
public class Provinceneighborslocal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "prov1_povince_id", referencedColumnName = "povince_id")
    @ManyToOne
    private Provincelocal prov1PovinceId;
    @JoinColumn(name = "prov2_povince_id", referencedColumnName = "povince_id")
    @ManyToOne
    private Provincelocal prov2PovinceId;

    public Provinceneighborslocal() {
    }

    public Provinceneighborslocal(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provincelocal getProv1PovinceId() {
        return prov1PovinceId;
    }

    public void setProv1PovinceId(Provincelocal prov1PovinceId) {
        this.prov1PovinceId = prov1PovinceId;
    }

    public Provincelocal getProv2PovinceId() {
        return prov2PovinceId;
    }

    public void setProv2PovinceId(Provincelocal prov2PovinceId) {
        this.prov2PovinceId = prov2PovinceId;
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
        if (!(object instanceof Provinceneighborslocal)) {
            return false;
        }
        Provinceneighborslocal other = (Provinceneighborslocal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Provinceneighborslocal[ id=" + id + " ]";
    }
    
}
