/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "building")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Building.findAll", query = "SELECT b FROM Building b"),
    @NamedQuery(name = "Building.findByIdBuilding", query = "SELECT b FROM Building b WHERE b.idBuilding = :idBuilding"),
    @NamedQuery(name = "Building.findByBuildingName", query = "SELECT b FROM Building b WHERE b.buildingName = :buildingName"),
    @NamedQuery(name = "Building.findByCost", query = "SELECT b FROM Building b WHERE b.cost = :cost")})
public class Building implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_building")
    private Integer idBuilding;
    @Basic(optional = false)
    @Column(name = "building_name")
    private String buildingName;
    @Basic(optional = false)
    @Column(name = "cost")
    private int cost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingIdBuilding")
    private Collection<TransactionBuild> transactionBuildCollection;
    @JoinColumn(name = "province_id_province", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceIdProvince;
    @JoinColumn(name = "resource_id_resurce", referencedColumnName = "id_resurce")
    @ManyToOne(optional = false)
    private Resource resourceIdResurce;

    public Building() {
    }

    public Building(Integer idBuilding) {
        this.idBuilding = idBuilding;
    }

    public Building(Integer idBuilding, String buildingName, int cost) {
        this.idBuilding = idBuilding;
        this.buildingName = buildingName;
        this.cost = cost;
    }

    public Integer getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Integer idBuilding) {
        this.idBuilding = idBuilding;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @XmlTransient
    public Collection<TransactionBuild> getTransactionBuildCollection() {
        return transactionBuildCollection;
    }

    public void setTransactionBuildCollection(Collection<TransactionBuild> transactionBuildCollection) {
        this.transactionBuildCollection = transactionBuildCollection;
    }

    public Province getProvinceIdProvince() {
        return provinceIdProvince;
    }

    public void setProvinceIdProvince(Province provinceIdProvince) {
        this.provinceIdProvince = provinceIdProvince;
    }

    public Resource getResourceIdResurce() {
        return resourceIdResurce;
    }

    public void setResourceIdResurce(Resource resourceIdResurce) {
        this.resourceIdResurce = resourceIdResurce;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBuilding != null ? idBuilding.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Building)) {
            return false;
        }
        Building other = (Building) object;
        if ((this.idBuilding == null && other.idBuilding != null) || (this.idBuilding != null && !this.idBuilding.equals(other.idBuilding))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Building[ idBuilding=" + idBuilding + " ]";
    }
    
}
