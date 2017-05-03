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
@Table(name = "resources")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resources.findAll", query = "SELECT r FROM Resources r"),
    @NamedQuery(name = "Resources.findByIdResources", query = "SELECT r FROM Resources r WHERE r.idResources = :idResources"),
    @NamedQuery(name = "Resources.findByAmount", query = "SELECT r FROM Resources r WHERE r.amount = :amount")})
public class Resources implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resources")
    private Integer idResources;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "clan_id_clan", referencedColumnName = "id_clan")
    @ManyToOne(optional = false)
    private Clan clanIdClan;
    @JoinColumn(name = "resource_id_resurce", referencedColumnName = "id_resurce")
    @ManyToOne(optional = false)
    private Resource resourceIdResurce;

    public Resources() {
    }

    public Resources(Integer idResources) {
        this.idResources = idResources;
    }

    public Resources(Integer idResources, int amount) {
        this.idResources = idResources;
        this.amount = amount;
    }

    public Integer getIdResources() {
        return idResources;
    }

    public void setIdResources(Integer idResources) {
        this.idResources = idResources;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Clan getClanIdClan() {
        return clanIdClan;
    }

    public void setClanIdClan(Clan clanIdClan) {
        this.clanIdClan = clanIdClan;
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
        hash += (idResources != null ? idResources.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resources)) {
            return false;
        }
        Resources other = (Resources) object;
        if ((this.idResources == null && other.idResources != null) || (this.idResources != null && !this.idResources.equals(other.idResources))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Resources[ idResources=" + idResources + " ]";
    }
    
}
