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
    @NamedQuery(name = "Province.findByNameProvince", query = "SELECT p FROM Province p WHERE p.nameProvince = :nameProvince"),
    @NamedQuery(name = "Province.findByX", query = "SELECT p FROM Province p WHERE p.x = :x"),
    @NamedQuery(name = "Province.findByY", query = "SELECT p FROM Province p WHERE p.y = :y"),
    @NamedQuery(name = "Province.findByArmyUnits", query = "SELECT p FROM Province p WHERE p.armyUnits = :armyUnits"),
    @NamedQuery(name = "Province.findByCostOneArmyUnitValue", query = "SELECT p FROM Province p WHERE p.costOneArmyUnitValue = :costOneArmyUnitValue"),
    @NamedQuery(name = "Province.findByCostOneArmyUnitResourceId", query = "SELECT p FROM Province p WHERE p.costOneArmyUnitResourceId = :costOneArmyUnitResourceId")})
public class Province implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_province")
    private Integer idProvince;
    @Basic(optional = false)
    @Column(name = "name_province")
    private String nameProvince;
    @Basic(optional = false)
    @Column(name = "x")
    private int x;
    @Basic(optional = false)
    @Column(name = "y")
    private int y;
    @Basic(optional = false)
    @Column(name = "army_units")
    private int armyUnits;
    @Basic(optional = false)
    @Column(name = "cost_one_army_unit_value")
    private int costOneArmyUnitValue;
    @Basic(optional = false)
    @Column(name = "cost_one_army_unit_resource_id")
    private int costOneArmyUnitResourceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceFromId")
    private Collection<TransactionMove> transactionMoveCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceToId")
    private Collection<TransactionMove> transactionMoveCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firstProvinceId")
    private Collection<Neighbour> neighbourCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secondProvinceId")
    private Collection<Neighbour> neighbourCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceId")
    private Collection<TransactionTrain> transactionTrainCollection;
    @JoinColumn(name = "clan_control_id", referencedColumnName = "id_clan")
    @ManyToOne
    private Clan clanControlId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceId")
    private Collection<TransactionBuild> transactionBuildCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceId")
    private Collection<OwnedBuildings> ownedBuildingsCollection;
    @OneToMany(mappedBy = "provinceId")
    private Collection<Simday> simdayCollection;

    public Province() {
    }

    public Province(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public Province(Integer idProvince, String nameProvince, int x, int y, int armyUnits, int costOneArmyUnitValue, int costOneArmyUnitResourceId) {
        this.idProvince = idProvince;
        this.nameProvince = nameProvince;
        this.x = x;
        this.y = y;
        this.armyUnits = armyUnits;
        this.costOneArmyUnitValue = costOneArmyUnitValue;
        this.costOneArmyUnitResourceId = costOneArmyUnitResourceId;
    }

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
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

    public int getArmyUnits() {
        return armyUnits;
    }

    public void setArmyUnits(int armyUnits) {
        this.armyUnits = armyUnits;
    }

    public int getCostOneArmyUnitValue() {
        return costOneArmyUnitValue;
    }

    public void setCostOneArmyUnitValue(int costOneArmyUnitValue) {
        this.costOneArmyUnitValue = costOneArmyUnitValue;
    }

    public int getCostOneArmyUnitResourceId() {
        return costOneArmyUnitResourceId;
    }

    public void setCostOneArmyUnitResourceId(int costOneArmyUnitResourceId) {
        this.costOneArmyUnitResourceId = costOneArmyUnitResourceId;
    }

    @XmlTransient
    public Collection<TransactionMove> getTransactionMoveCollection() {
        return transactionMoveCollection;
    }

    public void setTransactionMoveCollection(Collection<TransactionMove> transactionMoveCollection) {
        this.transactionMoveCollection = transactionMoveCollection;
    }

    @XmlTransient
    public Collection<TransactionMove> getTransactionMoveCollection1() {
        return transactionMoveCollection1;
    }

    public void setTransactionMoveCollection1(Collection<TransactionMove> transactionMoveCollection1) {
        this.transactionMoveCollection1 = transactionMoveCollection1;
    }

    @XmlTransient
    public Collection<Neighbour> getNeighbourCollection() {
        return neighbourCollection;
    }

    public void setNeighbourCollection(Collection<Neighbour> neighbourCollection) {
        this.neighbourCollection = neighbourCollection;
    }

    @XmlTransient
    public Collection<Neighbour> getNeighbourCollection1() {
        return neighbourCollection1;
    }

    public void setNeighbourCollection1(Collection<Neighbour> neighbourCollection1) {
        this.neighbourCollection1 = neighbourCollection1;
    }

    @XmlTransient
    public Collection<TransactionTrain> getTransactionTrainCollection() {
        return transactionTrainCollection;
    }

    public void setTransactionTrainCollection(Collection<TransactionTrain> transactionTrainCollection) {
        this.transactionTrainCollection = transactionTrainCollection;
    }

    public Clan getClanControlId() {
        return clanControlId;
    }

    public void setClanControlId(Clan clanControlId) {
        this.clanControlId = clanControlId;
    }

    @XmlTransient
    public Collection<TransactionBuild> getTransactionBuildCollection() {
        return transactionBuildCollection;
    }

    public void setTransactionBuildCollection(Collection<TransactionBuild> transactionBuildCollection) {
        this.transactionBuildCollection = transactionBuildCollection;
    }

    @XmlTransient
    public Collection<OwnedBuildings> getOwnedBuildingsCollection() {
        return ownedBuildingsCollection;
    }

    public void setOwnedBuildingsCollection(Collection<OwnedBuildings> ownedBuildingsCollection) {
        this.ownedBuildingsCollection = ownedBuildingsCollection;
    }

    @XmlTransient
    public Collection<Simday> getSimdayCollection() {
        return simdayCollection;
    }

    public void setSimdayCollection(Collection<Simday> simdayCollection) {
        this.simdayCollection = simdayCollection;
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
