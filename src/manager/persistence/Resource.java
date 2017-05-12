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
@Table(name = "resource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resource.findAll", query = "SELECT r FROM Resource r"),
    @NamedQuery(name = "Resource.findByIdResurce", query = "SELECT r FROM Resource r WHERE r.idResurce = :idResurce"),
    @NamedQuery(name = "Resource.findByResourceName", query = "SELECT r FROM Resource r WHERE r.resourceName = :resourceName")})
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resurce")
    private Integer idResurce;
    @Basic(optional = false)
    @Column(name = "resource_name")
    private String resourceName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceIdResurce")
    private Collection<Resources> resourcesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceIdResurce")
    private Collection<Building> buildingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceIdResurce")
    private Collection<TransactionTrain> transactionTrainCollection;

    public Resource() {
    }

    public Resource(Integer idResurce) {
        this.idResurce = idResurce;
    }

    public Resource(Integer idResurce, String resourceName) {
        this.idResurce = idResurce;
        this.resourceName = resourceName;
    }

    public Integer getIdResurce() {
        return idResurce;
    }

    public void setIdResurce(Integer idResurce) {
        this.idResurce = idResurce;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @XmlTransient
    public Collection<Resources> getResourcesCollection() {
        return resourcesCollection;
    }

    public void setResourcesCollection(Collection<Resources> resourcesCollection) {
        this.resourcesCollection = resourcesCollection;
    }

    @XmlTransient
    public Collection<Building> getBuildingCollection() {
        return buildingCollection;
    }

    public void setBuildingCollection(Collection<Building> buildingCollection) {
        this.buildingCollection = buildingCollection;
    }

    @XmlTransient
    public Collection<TransactionTrain> getTransactionTrainCollection() {
        return transactionTrainCollection;
    }

    public void setTransactionTrainCollection(Collection<TransactionTrain> transactionTrainCollection) {
        this.transactionTrainCollection = transactionTrainCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResurce != null ? idResurce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.idResurce == null && other.idResurce != null) || (this.idResurce != null && !this.idResurce.equals(other.idResurce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Resource[ idResurce=" + idResurce + " ]";
    }
    
}
