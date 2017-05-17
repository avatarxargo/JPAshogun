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
@Table(name = "owned_buildings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OwnedBuildings.findAll", query = "SELECT o FROM OwnedBuildings o"),
    @NamedQuery(name = "OwnedBuildings.findByIdOwnedBuildings", query = "SELECT o FROM OwnedBuildings o WHERE o.idOwnedBuildings = :idOwnedBuildings"),
    @NamedQuery(name = "OwnedBuildings.findByAmountBuildings", query = "SELECT o FROM OwnedBuildings o WHERE o.amountBuildings = :amountBuildings")})
public class OwnedBuildings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_owned_buildings")
    private Integer idOwnedBuildings;
    @Basic(optional = false)
    @Column(name = "amount_buildings")
    private int amountBuildings;
    @JoinColumn(name = "building_id", referencedColumnName = "id_building")
    @ManyToOne(optional = false)
    private Building buildingId;
    @JoinColumn(name = "province_id", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceId;

    public OwnedBuildings() {
    }

    public OwnedBuildings(Integer idOwnedBuildings) {
        this.idOwnedBuildings = idOwnedBuildings;
    }

    public OwnedBuildings(Integer idOwnedBuildings, int amountBuildings) {
        this.idOwnedBuildings = idOwnedBuildings;
        this.amountBuildings = amountBuildings;
    }

    public Integer getIdOwnedBuildings() {
        return idOwnedBuildings;
    }

    public void setIdOwnedBuildings(Integer idOwnedBuildings) {
        this.idOwnedBuildings = idOwnedBuildings;
    }

    public int getAmountBuildings() {
        return amountBuildings;
    }

    public void setAmountBuildings(int amountBuildings) {
        this.amountBuildings = amountBuildings;
    }

    public Building getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Building buildingId) {
        this.buildingId = buildingId;
    }

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOwnedBuildings != null ? idOwnedBuildings.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OwnedBuildings)) {
            return false;
        }
        OwnedBuildings other = (OwnedBuildings) object;
        if ((this.idOwnedBuildings == null && other.idOwnedBuildings != null) || (this.idOwnedBuildings != null && !this.idOwnedBuildings.equals(other.idOwnedBuildings))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.OwnedBuildings[ idOwnedBuildings=" + idOwnedBuildings + " ]";
    }
    
}
