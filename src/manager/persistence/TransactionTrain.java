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
import manager.persistence.Province;
import manager.persistence.Simday;

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
    @NamedQuery(name = "TransactionTrain.findByCountArmy", query = "SELECT t FROM TransactionTrain t WHERE t.countArmy = :countArmy")})
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
    @JoinColumn(name = "province_id", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceId;
    @JoinColumn(name = "simday_number", referencedColumnName = "id_simday")
    @ManyToOne(optional = false)
    private Simday simdayNumber;

    public TransactionTrain() {
    }

    public TransactionTrain(Integer idTransactionTrain) {
        this.idTransactionTrain = idTransactionTrain;
    }

    public TransactionTrain(Integer idTransactionTrain, int countArmy) {
        this.idTransactionTrain = idTransactionTrain;
        this.countArmy = countArmy;
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

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    public Simday getSimdayNumber() {
        return simdayNumber;
    }

    public void setSimdayNumber(Simday simdayNumber) {
        this.simdayNumber = simdayNumber;
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
        return "manager.dialog.TransactionTrain[ idTransactionTrain=" + idTransactionTrain + " ]";
    }
    
}
