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
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByIdTransactions", query = "SELECT t FROM Transactions t WHERE t.idTransactions = :idTransactions")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transactions")
    private Integer idTransactions;
    @JoinColumn(name = "clan_id_clan", referencedColumnName = "id_clan")
    @ManyToOne(optional = false)
    private Clan clanIdClan;
    @JoinColumn(name = "simday_id_simday", referencedColumnName = "id_simday")
    @ManyToOne(optional = false)
    private Simday simdayIdSimday;
    @JoinColumn(name = "transaction_build_id_transaction_build", referencedColumnName = "id_transaction_build")
    @ManyToOne(optional = false)
    private TransactionBuild transactionBuildIdTransactionBuild;
    @JoinColumn(name = "transaction_move_id_transaction_move", referencedColumnName = "id_transaction_move")
    @ManyToOne(optional = false)
    private TransactionMove transactionMoveIdTransactionMove;
    @JoinColumn(name = "transaction_train_id_transaction_train", referencedColumnName = "id_transaction_train")
    @ManyToOne(optional = false)
    private TransactionTrain transactionTrainIdTransactionTrain;

    public Transactions() {
    }

    public Transactions(Integer idTransactions) {
        this.idTransactions = idTransactions;
    }

    public Integer getIdTransactions() {
        return idTransactions;
    }

    public void setIdTransactions(Integer idTransactions) {
        this.idTransactions = idTransactions;
    }

    public Clan getClanIdClan() {
        return clanIdClan;
    }

    public void setClanIdClan(Clan clanIdClan) {
        this.clanIdClan = clanIdClan;
    }

    public Simday getSimdayIdSimday() {
        return simdayIdSimday;
    }

    public void setSimdayIdSimday(Simday simdayIdSimday) {
        this.simdayIdSimday = simdayIdSimday;
    }

    public TransactionBuild getTransactionBuildIdTransactionBuild() {
        return transactionBuildIdTransactionBuild;
    }

    public void setTransactionBuildIdTransactionBuild(TransactionBuild transactionBuildIdTransactionBuild) {
        this.transactionBuildIdTransactionBuild = transactionBuildIdTransactionBuild;
    }

    public TransactionMove getTransactionMoveIdTransactionMove() {
        return transactionMoveIdTransactionMove;
    }

    public void setTransactionMoveIdTransactionMove(TransactionMove transactionMoveIdTransactionMove) {
        this.transactionMoveIdTransactionMove = transactionMoveIdTransactionMove;
    }

    public TransactionTrain getTransactionTrainIdTransactionTrain() {
        return transactionTrainIdTransactionTrain;
    }

    public void setTransactionTrainIdTransactionTrain(TransactionTrain transactionTrainIdTransactionTrain) {
        this.transactionTrainIdTransactionTrain = transactionTrainIdTransactionTrain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransactions != null ? idTransactions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.idTransactions == null && other.idTransactions != null) || (this.idTransactions != null && !this.idTransactions.equals(other.idTransactions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Transactions[ idTransactions=" + idTransactions + " ]";
    }
    
}
