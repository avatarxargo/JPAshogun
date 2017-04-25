/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class ProvinceNeighbors implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Province prov1;
    @OneToOne
    private Province prov2;
    
    public static ProvinceNeighbors createNeighbors(Province from, Province to) {
        ProvinceNeighbors ret = new ProvinceNeighbors();
        ret.prov1 = from;
        ret.prov2 = to;
        return ret;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Province)) {
            return false;
        }
        ProvinceNeighbors other = (ProvinceNeighbors) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the prov1
     */
    public Province getProv1() {
        return prov1;
    }

    /**
     * @param prov1 the prov1 to set
     */
    public void setProv1(Province prov1) {
        this.prov1 = prov1;
    }

    /**
     * @return the prov2
     */
    public Province getProv2() {
        return prov2;
    }

    /**
     * @param prov2 the prov2 to set
     */
    public void setProv2(Province prov2) {
        this.prov2 = prov2;
    }
}
