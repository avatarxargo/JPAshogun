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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "neighbour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Neighbour.findAll", query = "SELECT n FROM Neighbour n"),
    @NamedQuery(name = "Neighbour.findByIdNeighbour", query = "SELECT n FROM Neighbour n WHERE n.idNeighbour = :idNeighbour")})
public class Neighbour implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_neighbour")
    private Integer idNeighbour;
    @JoinColumn(name = "first_province_id", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province firstProvinceId;
    @JoinColumn(name = "second_province_id", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province secondProvinceId;

    public Neighbour() {
    }

    public Neighbour(Integer idNeighbour) {
        this.idNeighbour = idNeighbour;
    }

    public Integer getIdNeighbour() {
        return idNeighbour;
    }

    public void setIdNeighbour(Integer idNeighbour) {
        this.idNeighbour = idNeighbour;
    }

    public Province getFirstProvinceId() {
        return firstProvinceId;
    }

    public void setFirstProvinceId(Province firstProvinceId) {
        this.firstProvinceId = firstProvinceId;
    }

    public Province getSecondProvinceId() {
        return secondProvinceId;
    }

    public void setSecondProvinceId(Province secondProvinceId) {
        this.secondProvinceId = secondProvinceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNeighbour != null ? idNeighbour.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Neighbour)) {
            return false;
        }
        Neighbour other = (Neighbour) object;
        if ((this.idNeighbour == null && other.idNeighbour != null) || (this.idNeighbour != null && !this.idNeighbour.equals(other.idNeighbour))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Neighbour[ idNeighbour=" + idNeighbour + " ]";
    }
    
}
