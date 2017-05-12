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
@Table(name = "clan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clan.findAll", query = "SELECT c FROM Clan c"),
    @NamedQuery(name = "Clan.findByIdClan", query = "SELECT c FROM Clan c WHERE c.idClan = :idClan"),
    @NamedQuery(name = "Clan.findByNameclan", query = "SELECT c FROM Clan c WHERE c.nameclan = :nameclan")})
public class Clan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clan")
    private Integer idClan;
    @Basic(optional = false)
    @Column(name = "nameclan")
    private String nameclan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clanIdClan")
    private Collection<Resources> resourcesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clanIdClan")
    private Collection<Transactions> transactionsCollection;
    @OneToMany(mappedBy = "clanIdClan")
    private Collection<Province> provinceCollection;
    @OneToMany(mappedBy = "clanIdClan")
    private Collection<Player> playerCollection;

    public Clan() {
    }

    public Clan(Integer idClan) {
        this.idClan = idClan;
    }

    public Clan(Integer idClan, String nameclan) {
        this.idClan = idClan;
        this.nameclan = nameclan;
    }

    public Integer getIdClan() {
        return idClan;
    }

    public void setIdClan(Integer idClan) {
        this.idClan = idClan;
    }

    public String getNameclan() {
        return nameclan;
    }

    public void setNameclan(String nameclan) {
        this.nameclan = nameclan;
    }

    @XmlTransient
    public Collection<Resources> getResourcesCollection() {
        return resourcesCollection;
    }

    public void setResourcesCollection(Collection<Resources> resourcesCollection) {
        this.resourcesCollection = resourcesCollection;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    @XmlTransient
    public Collection<Province> getProvinceCollection() {
        return provinceCollection;
    }

    public void setProvinceCollection(Collection<Province> provinceCollection) {
        this.provinceCollection = provinceCollection;
    }

    @XmlTransient
    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    public void setPlayerCollection(Collection<Player> playerCollection) {
        this.playerCollection = playerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClan != null ? idClan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clan)) {
            return false;
        }
        Clan other = (Clan) object;
        if ((this.idClan == null && other.idClan != null) || (this.idClan != null && !this.idClan.equals(other.idClan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Clan[ idClan=" + idClan + " ]";
    }
    
}
