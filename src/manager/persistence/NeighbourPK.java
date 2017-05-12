/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author user
 */
@Embeddable
public class NeighbourPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "province_id_province1")
    private int provinceIdProvince1;
    @Basic(optional = false)
    @Column(name = "province_id_province2")
    private int provinceIdProvince2;

    public NeighbourPK() {
    }

    public NeighbourPK(int provinceIdProvince1, int provinceIdProvince2) {
        this.provinceIdProvince1 = provinceIdProvince1;
        this.provinceIdProvince2 = provinceIdProvince2;
    }

    public int getProvinceIdProvince1() {
        return provinceIdProvince1;
    }

    public void setProvinceIdProvince1(int provinceIdProvince1) {
        this.provinceIdProvince1 = provinceIdProvince1;
    }

    public int getProvinceIdProvince2() {
        return provinceIdProvince2;
    }

    public void setProvinceIdProvince2(int provinceIdProvince2) {
        this.provinceIdProvince2 = provinceIdProvince2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) provinceIdProvince1;
        hash += (int) provinceIdProvince2;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NeighbourPK)) {
            return false;
        }
        NeighbourPK other = (NeighbourPK) object;
        if (this.provinceIdProvince1 != other.provinceIdProvince1) {
            return false;
        }
        if (this.provinceIdProvince2 != other.provinceIdProvince2) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.NeighbourPK[ provinceIdProvince1=" + provinceIdProvince1 + ", provinceIdProvince2=" + provinceIdProvince2 + " ]";
    }
    
}
