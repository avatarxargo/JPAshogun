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
@Table(name = "transaction_build")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionBuild.findAll", query = "SELECT t FROM TransactionBuild t"),
    @NamedQuery(name = "TransactionBuild.findByIdTransactionBuild", query = "SELECT t FROM TransactionBuild t WHERE t.idTransactionBuild = :idTransactionBuild"),
    @NamedQuery(name = "TransactionBuild.findByCount", query = "SELECT t FROM TransactionBuild t WHERE t.count = :count")})
public class TransactionBuild implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transaction_build")
    private Integer idTransactionBuild;
    @Basic(optional = false)
    @Column(name = "count")
    private int count;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionBuildIdTransactionBuild")
    private Collection<Transactions> transactionsCollection;
    @JoinColumn(name = "building_id_building", referencedColumnName = "id_building")
    @ManyToOne(optional = false)
    private Building buildingIdBuilding;
    @JoinColumn(name = "province_id_province", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceIdProvince;

    public TransactionBuild() {
    }

    public TransactionBuild(Integer idTransactionBuild) {
        this.idTransactionBuild = idTransactionBuild;
    }

    public TransactionBuild(Integer idTransactionBuild, int count) {
        this.idTransactionBuild = idTransactionBuild;
        this.count = count;
    }

    public Integer getIdTransactionBuild() {
        return idTransactionBuild;
    }

    public void setIdTransactionBuild(Integer idTransactionBuild) {
        this.idTransactionBuild = idTransactionBuild;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    public Building getBuildingIdBuilding() {
        return buildingIdBuilding;
    }

    public void setBuildingIdBuilding(Building buildingIdBuilding) {
        this.buildingIdBuilding = buildingIdBuilding;
    }

    public Province getProvinceIdProvince() {
        return provinceIdProvince;
    }

    public void setProvinceIdProvince(Province provinceIdProvince) {
        this.provinceIdProvince = provinceIdProvince;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransactionBuild != null ? idTransactionBuild.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionBuild)) {
            return false;
        }
        TransactionBuild other = (TransactionBuild) object;
        if ((this.idTransactionBuild == null && other.idTransactionBuild != null) || (this.idTransactionBuild != null && !this.idTransactionBuild.equals(other.idTransactionBuild))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.TransactionBuild[ idTransactionBuild=" + idTransactionBuild + " ]";
    }
    
}
