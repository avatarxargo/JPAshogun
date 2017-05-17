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
    @NamedQuery(name = "Resource.findByIdResource", query = "SELECT r FROM Resource r WHERE r.idResource = :idResource"),
    @NamedQuery(name = "Resource.findByNameResource", query = "SELECT r FROM Resource r WHERE r.nameResource = :nameResource")})
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resource")
    private Integer idResource;
    @Basic(optional = false)
    @Column(name = "name_resource")
    private String nameResource;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceId")
    private Collection<OwnedResource> ownedResourceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "costResourceId")
    private Collection<Building> buildingCollection;

    public Resource() {
    }

    public Resource(Integer idResource) {
        this.idResource = idResource;
    }

    public Resource(Integer idResource, String nameResource) {
        this.idResource = idResource;
        this.nameResource = nameResource;
    }

    public Integer getIdResource() {
        return idResource;
    }

    public void setIdResource(Integer idResource) {
        this.idResource = idResource;
    }

    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    @XmlTransient
    public Collection<OwnedResource> getOwnedResourceCollection() {
        return ownedResourceCollection;
    }

    public void setOwnedResourceCollection(Collection<OwnedResource> ownedResourceCollection) {
        this.ownedResourceCollection = ownedResourceCollection;
    }

    @XmlTransient
    public Collection<Building> getBuildingCollection() {
        return buildingCollection;
    }

    public void setBuildingCollection(Collection<Building> buildingCollection) {
        this.buildingCollection = buildingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResource != null ? idResource.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.idResource == null && other.idResource != null) || (this.idResource != null && !this.idResource.equals(other.idResource))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Resource[ idResource=" + idResource + " ]";
    }
    
}
