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
@Table(name = "transaction_train")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionTrain.findAll", query = "SELECT t FROM TransactionTrain t"),
    @NamedQuery(name = "TransactionTrain.findByIdTransactionTrain", query = "SELECT t FROM TransactionTrain t WHERE t.idTransactionTrain = :idTransactionTrain"),
    @NamedQuery(name = "TransactionTrain.findByCountArmy", query = "SELECT t FROM TransactionTrain t WHERE t.countArmy = :countArmy"),
    @NamedQuery(name = "TransactionTrain.findByResourcesAmount", query = "SELECT t FROM TransactionTrain t WHERE t.resourcesAmount = :resourcesAmount")})
public class TransactionTrain implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transaction_train")
    private Integer idTransactionTrain;
    @Basic(optional = false)
    @Column(name = "count_army")
    private int countArmy;
    @Basic(optional = false)
    @Column(name = "resources_amount")
    private int resourcesAmount;
    @JoinColumn(name = "province_id_province", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceIdProvince;
    @JoinColumn(name = "resource_id_resurce", referencedColumnName = "id_resurce")
    @ManyToOne(optional = false)
    private Resource resourceIdResurce;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionTrainIdTransactionTrain")
    private Collection<Transactions> transactionsCollection;

    public TransactionTrain() {
    }

    public TransactionTrain(Integer idTransactionTrain) {
        this.idTransactionTrain = idTransactionTrain;
    }

    public TransactionTrain(Integer idTransactionTrain, int countArmy, int resourcesAmount) {
        this.idTransactionTrain = idTransactionTrain;
        this.countArmy = countArmy;
        this.resourcesAmount = resourcesAmount;
    }

    public Integer getIdTransactionTrain() {
        return idTransactionTrain;
    }

    public void setIdTransactionTrain(Integer idTransactionTrain) {
        this.idTransactionTrain = idTransactionTrain;
    }

    public int getCountArmy() {
        return countArmy;
    }

    public void setCountArmy(int countArmy) {
        this.countArmy = countArmy;
    }

    public int getResourcesAmount() {
        return resourcesAmount;
    }

    public void setResourcesAmount(int resourcesAmount) {
        this.resourcesAmount = resourcesAmount;
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

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransactionTrain != null ? idTransactionTrain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionTrain)) {
            return false;
        }
        TransactionTrain other = (TransactionTrain) object;
        if ((this.idTransactionTrain == null && other.idTransactionTrain != null) || (this.idTransactionTrain != null && !this.idTransactionTrain.equals(other.idTransactionTrain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.TransactionTrain[ idTransactionTrain=" + idTransactionTrain + " ]";
    }
    
}
