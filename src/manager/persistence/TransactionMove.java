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
    @JoinColumn(name = "clan_issue_id", referencedColumnName = "id_clan")
    @ManyToOne(optional = false)
    private Clan clanIssueId;
    @JoinColumn(name = "province_from_id", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceFromId;
    @JoinColumn(name = "province_to_id", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceToId;
    @JoinColumn(name = "simday_number", referencedColumnName = "id_simday")
    @ManyToOne(optional = false)
    private Simday simdayNumber;

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

    public Clan getClanIssueId() {
        return clanIssueId;
    }

    public void setClanIssueId(Clan clanIssueId) {
        this.clanIssueId = clanIssueId;
    }

    public Province getProvinceFromId() {
        return provinceFromId;
    }

    public void setProvinceFromId(Province provinceFromId) {
        this.provinceFromId = provinceFromId;
    }

    public Province getProvinceToId() {
        return provinceToId;
    }

    public void setProvinceToId(Province provinceToId) {
        this.provinceToId = provinceToId;
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
