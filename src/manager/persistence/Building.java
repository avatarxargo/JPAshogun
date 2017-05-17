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
    @NamedQuery(name = "Building.findByNameBuilding", query = "SELECT b FROM Building b WHERE b.nameBuilding = :nameBuilding"),
    @NamedQuery(name = "Building.findByCostValue", query = "SELECT b FROM Building b WHERE b.costValue = :costValue")})
public class Building implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_building")
    private Integer idBuilding;
    @Basic(optional = false)
    @Column(name = "name_building")
    private String nameBuilding;
    @Basic(optional = false)
    @Column(name = "cost_value")
    private int costValue;
    @JoinColumn(name = "cost_resource_id", referencedColumnName = "id_resource")
    @ManyToOne(optional = false)
    private Resource costResourceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingTypeId")
    private Collection<TransactionBuild> transactionBuildCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingId")
    private Collection<OwnedBuildings> ownedBuildingsCollection;

    public Building() {
    }

    public Building(Integer idBuilding) {
        this.idBuilding = idBuilding;
    }

    public Building(Integer idBuilding, String nameBuilding, int costValue) {
        this.idBuilding = idBuilding;
        this.nameBuilding = nameBuilding;
        this.costValue = costValue;
    }

    public Integer getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Integer idBuilding) {
        this.idBuilding = idBuilding;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public int getCostValue() {
        return costValue;
    }

    public void setCostValue(int costValue) {
        this.costValue = costValue;
    }

    public Resource getCostResourceId() {
        return costResourceId;
    }

    public void setCostResourceId(Resource costResourceId) {
        this.costResourceId = costResourceId;
    }

    @XmlTransient
    public Collection<TransactionBuild> getTransactionBuildCollection() {
        return transactionBuildCollection;
    }

    public void setTransactionBuildCollection(Collection<TransactionBuild> transactionBuildCollection) {
        this.transactionBuildCollection = transactionBuildCollection;
    }

    @XmlTransient
    public Collection<OwnedBuildings> getOwnedBuildingsCollection() {
        return ownedBuildingsCollection;
    }

    public void setOwnedBuildingsCollection(Collection<OwnedBuildings> ownedBuildingsCollection) {
        this.ownedBuildingsCollection = ownedBuildingsCollection;
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
