/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "neighbour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Neighbour.findAll", query = "SELECT n FROM Neighbour n"),
    @NamedQuery(name = "Neighbour.findByProvinceIdProvince1", query = "SELECT n FROM Neighbour n WHERE n.neighbourPK.provinceIdProvince1 = :provinceIdProvince1"),
    @NamedQuery(name = "Neighbour.findByProvinceIdProvince2", query = "SELECT n FROM Neighbour n WHERE n.neighbourPK.provinceIdProvince2 = :provinceIdProvince2")})
public class Neighbour implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NeighbourPK neighbourPK;

    public Neighbour() {
    }

    public Neighbour(NeighbourPK neighbourPK) {
        this.neighbourPK = neighbourPK;
    }

    public Neighbour(int provinceIdProvince1, int provinceIdProvince2) {
        this.neighbourPK = new NeighbourPK(provinceIdProvince1, provinceIdProvince2);
    }

    public NeighbourPK getNeighbourPK() {
        return neighbourPK;
    }

    public void setNeighbourPK(NeighbourPK neighbourPK) {
        this.neighbourPK = neighbourPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (neighbourPK != null ? neighbourPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Neighbour)) {
            return false;
        }
        Neighbour other = (Neighbour) object;
        if ((this.neighbourPK == null && other.neighbourPK != null) || (this.neighbourPK != null && !this.neighbourPK.equals(other.neighbourPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Neighbour[ neighbourPK=" + neighbourPK + " ]";
    }
    
}
