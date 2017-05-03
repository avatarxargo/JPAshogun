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
@Table(name = "transaction_move")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionMove.findAll", query = "SELECT t FROM TransactionMove t"),
    @NamedQuery(name = "TransactionMove.findByIdTransactionMove", query = "SELECT t FROM TransactionMove t WHERE t.idTransactionMove = :idTransactionMove"),
    @NamedQuery(name = "TransactionMove.findByArmyUnits", query = "SELECT t FROM TransactionMove t WHERE t.armyUnits = :armyUnits")})
public class TransactionMove implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transaction_move")
    private Integer idTransactionMove;
    @Column(name = "army_units")
    private Integer armyUnits;
    @JoinColumn(name = "province_id_province", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceIdProvince;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionMoveIdTransactionMove")
    private Collection<Transactions> transactionsCollection;

    public TransactionMove() {
    }

    public TransactionMove(Integer idTransactionMove) {
        this.idTransactionMove = idTransactionMove;
    }

    public Integer getIdTransactionMove() {
        return idTransactionMove;
    }

    public void setIdTransactionMove(Integer idTransactionMove) {
        this.idTransactionMove = idTransactionMove;
    }

    public Integer getArmyUnits() {
        return armyUnits;
    }

    public void setArmyUnits(Integer armyUnits) {
        this.armyUnits = armyUnits;
    }

    public Province getProvinceIdProvince() {
        return provinceIdProvince;
    }

    public void setProvinceIdProvince(Province provinceIdProvince) {
        this.provinceIdProvince = provinceIdProvince;
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
        hash += (idTransactionMove != null ? idTransactionMove.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionMove)) {
            return false;
        }
        TransactionMove other = (TransactionMove) object;
        if ((this.idTransactionMove == null && other.idTransactionMove != null) || (this.idTransactionMove != null && !this.idTransactionMove.equals(other.idTransactionMove))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.TransactionMove[ idTransactionMove=" + idTransactionMove + " ]";
    }
    
}
