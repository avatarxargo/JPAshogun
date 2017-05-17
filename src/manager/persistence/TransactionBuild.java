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
import manager.persistence.Building;
import manager.persistence.Province;
import manager.persistence.Simday;

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
    @NamedQuery(name = "TransactionBuild.findByCountBuildings", query = "SELECT t FROM TransactionBuild t WHERE t.countBuildings = :countBuildings")})
public class TransactionBuild implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transaction_build")
    private Integer idTransactionBuild;
    @Basic(optional = false)
    @Column(name = "count_buildings")
    private int countBuildings;
    @JoinColumn(name = "building_type_id", referencedColumnName = "id_building")
    @ManyToOne(optional = false)
    private Building buildingTypeId;
    @JoinColumn(name = "province_id", referencedColumnName = "id_province")
    @ManyToOne(optional = false)
    private Province provinceId;
    @JoinColumn(name = "simday_number", referencedColumnName = "id_simday")
    @ManyToOne(optional = false)
    private Simday simdayNumber;

    public TransactionBuild() {
    }

    public TransactionBuild(Integer idTransactionBuild) {
        this.idTransactionBuild = idTransactionBuild;
    }

    public TransactionBuild(Integer idTransactionBuild, int countBuildings) {
        this.idTransactionBuild = idTransactionBuild;
        this.countBuildings = countBuildings;
    }

    public Integer getIdTransactionBuild() {
        return idTransactionBuild;
    }

    public void setIdTransactionBuild(Integer idTransactionBuild) {
        this.idTransactionBuild = idTransactionBuild;
    }

    public int getCountBuildings() {
        return countBuildings;
    }

    public void setCountBuildings(int countBuildings) {
        this.countBuildings = countBuildings;
    }

    public Building getBuildingTypeId() {
        return buildingTypeId;
    }

    public void setBuildingTypeId(Building buildingTypeId) {
        this.buildingTypeId = buildingTypeId;
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
        return "manager.dialog.TransactionBuild[ idTransactionBuild=" + idTransactionBuild + " ]";
    }
    
}
