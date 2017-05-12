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
@Table(name = "province")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Province.findAll", query = "SELECT p FROM Province p"),
    @NamedQuery(name = "Province.findByIdProvince", query = "SELECT p FROM Province p WHERE p.idProvince = :idProvince"),
    @NamedQuery(name = "Province.findByArmyUnits", query = "SELECT p FROM Province p WHERE p.armyUnits = :armyUnits"),
    @NamedQuery(name = "Province.findByX", query = "SELECT p FROM Province p WHERE p.x = :x"),
    @NamedQuery(name = "Province.findByY", query = "SELECT p FROM Province p WHERE p.y = :y"),
    @NamedQuery(name = "Province.findByProvinceName", query = "SELECT p FROM Province p WHERE p.provinceName = :provinceName")})
public class Province implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_province")
    private Integer idProvince;
    @Basic(optional = false)
    @Column(name = "army_units")
    private int armyUnits;
    @Basic(optional = false)
    @Column(name = "x")
    private int x;
    @Basic(optional = false)
    @Column(name = "y")
    private int y;
    @Basic(optional = false)
    @Column(name = "province_name")
    private String provinceName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceIdProvince")
    private Collection<TransactionMove> transactionMoveCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceIdProvince")
    private Collection<TransactionTrain> transactionTrainCollection;
    @JoinColumn(name = "clan_id_clan", referencedColumnName = "id_clan")
    @ManyToOne
    private Clan clanIdClan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceIdProvince")
    private Collection<TransactionBuild> transactionBuildCollection;

    public Province() {
    }

    public Province(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public Province(Integer idProvince, int armyUnits, int x, int y, String provinceName) {
        this.idProvince = idProvince;
        this.armyUnits = armyUnits;
        this.x = x;
        this.y = y;
        this.provinceName = provinceName;
    }

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public int getArmyUnits() {
        return armyUnits;
    }

    public void setArmyUnits(int armyUnits) {
        this.armyUnits = armyUnits;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public Clan getClanIdClan() {
        return clanIdClan;
    }

    public void setClanIdClan(Clan clanIdClan) {
        this.clanIdClan = clanIdClan;
    }

    @XmlTransient
    public Collection<TransactionBuild> getTransactionBuildCollection() {
        return transactionBuildCollection;
    }

    public void setTransactionBuildCollection(Collection<TransactionBuild> transactionBuildCollection) {
        this.transactionBuildCollection = transactionBuildCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvince != null ? idProvince.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Province)) {
            return false;
        }
        Province other = (Province) object;
        if ((this.idProvince == null && other.idProvince != null) || (this.idProvince != null && !this.idProvince.equals(other.idProvince))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Province[ idProvince=" + idProvince + " ]";
    }
    
}
