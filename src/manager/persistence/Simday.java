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
@Table(name = "simday")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Simday.findAll", query = "SELECT s FROM Simday s"),
    @NamedQuery(name = "Simday.findByIdSimday", query = "SELECT s FROM Simday s WHERE s.idSimday = :idSimday"),
    @NamedQuery(name = "Simday.findByDayNumber", query = "SELECT s FROM Simday s WHERE s.dayNumber = :dayNumber")})
public class Simday implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_simday")
    private Integer idSimday;
    @Basic(optional = false)
    @Column(name = "day_number")
    private int dayNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "simdayNumber")
    private Collection<TransactionMove> transactionMoveCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "simdayNumber")
    private Collection<TransactionTrain> transactionTrainCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "simdayNumber")
    private Collection<TransactionBuild> transactionBuildCollection;
    @JoinColumn(name = "clan_id", referencedColumnName = "id_clan")
    @ManyToOne(optional = false)
    private Clan clanId;
    @JoinColumn(name = "province_id", referencedColumnName = "id_province")
    @ManyToOne
    private Province provinceId;

    public Simday() {
    }

    public Simday(Integer idSimday) {
        this.idSimday = idSimday;
    }

    public Simday(Integer idSimday, int dayNumber) {
        this.idSimday = idSimday;
        this.dayNumber = dayNumber;
    }

    public Integer getIdSimday() {
        return idSimday;
    }

    public void setIdSimday(Integer idSimday) {
        this.idSimday = idSimday;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    @XmlTransient
    public Collection<TransactionMove> getTransactionMoveCollection() {
        return transactionMoveCollection;
    }

    public void setTransactionMoveCollection(Collection<TransactionMove> transactionMoveCollection) {
        this.transactionMoveCollection = transactionMoveCollection;
    }

    @XmlTransient
    public Collection<TransactionTrain> getTransactionTrainCollection() {
        return transactionTrainCollection;
    }

    public void setTransactionTrainCollection(Collection<TransactionTrain> transactionTrainCollection) {
        this.transactionTrainCollection = transactionTrainCollection;
    }

    @XmlTransient
    public Collection<TransactionBuild> getTransactionBuildCollection() {
        return transactionBuildCollection;
    }

    public void setTransactionBuildCollection(Collection<TransactionBuild> transactionBuildCollection) {
        this.transactionBuildCollection = transactionBuildCollection;
    }

    public Clan getClanId() {
        return clanId;
    }

    public void setClanId(Clan clanId) {
        this.clanId = clanId;
    }

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSimday != null ? idSimday.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Simday)) {
            return false;
        }
        Simday other = (Simday) object;
        if ((this.idSimday == null && other.idSimday != null) || (this.idSimday != null && !this.idSimday.equals(other.idSimday))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Simday[ idSimday=" + idSimday + " ]";
    }
    
}
