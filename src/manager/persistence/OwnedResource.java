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
@Table(name = "owned_resource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OwnedResource.findAll", query = "SELECT o FROM OwnedResource o"),
    @NamedQuery(name = "OwnedResource.findByIdOwnedResources", query = "SELECT o FROM OwnedResource o WHERE o.idOwnedResources = :idOwnedResources"),
    @NamedQuery(name = "OwnedResource.findByAmountResources", query = "SELECT o FROM OwnedResource o WHERE o.amountResources = :amountResources")})
public class OwnedResource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_owned_resources")
    private Integer idOwnedResources;
    @Basic(optional = false)
    @Column(name = "amount_resources")
    private int amountResources;
    @JoinColumn(name = "clan_id", referencedColumnName = "id_clan")
    @ManyToOne(optional = false)
    private Clan clanId;
    @JoinColumn(name = "resource_id", referencedColumnName = "id_resource")
    @ManyToOne(optional = false)
    private Resource resourceId;

    public OwnedResource() {
    }

    public OwnedResource(Integer idOwnedResources) {
        this.idOwnedResources = idOwnedResources;
    }

    public OwnedResource(Integer idOwnedResources, int amountResources) {
        this.idOwnedResources = idOwnedResources;
        this.amountResources = amountResources;
    }

    public Integer getIdOwnedResources() {
        return idOwnedResources;
    }

    public void setIdOwnedResources(Integer idOwnedResources) {
        this.idOwnedResources = idOwnedResources;
    }

    public int getAmountResources() {
        return amountResources;
    }

    public void setAmountResources(int amountResources) {
        this.amountResources = amountResources;
    }

    public Clan getClanId() {
        return clanId;
    }

    public void setClanId(Clan clanId) {
        this.clanId = clanId;
    }

    public Resource getResourceId() {
        return resourceId;
    }

    public void setResourceId(Resource resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOwnedResources != null ? idOwnedResources.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OwnedResource)) {
            return false;
        }
        OwnedResource other = (OwnedResource) object;
        if ((this.idOwnedResources == null && other.idOwnedResources != null) || (this.idOwnedResources != null && !this.idOwnedResources.equals(other.idOwnedResources))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.OwnedResource[ idOwnedResources=" + idOwnedResources + " ]";
    }
    
}
