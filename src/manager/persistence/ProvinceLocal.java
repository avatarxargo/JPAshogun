/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author user
 */
@Entity
public class ProvinceLocal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "povince_id", updatable = false, nullable = false)
    private Long province_id;
    private String province_name;
    private float x;
    private float y;

    public static ProvinceLocal createProvince(String name, float x, float y) {
        ProvinceLocal ret = new ProvinceLocal();
        ret.setProvince_name(name);
        ret.setX(x);
        ret.setY(y);
        //ret.setNeighbors(neighbors);
        return ret;
    }
    
    public Long getId() {
        return province_id;
    }

    public void setId(Long id) {
        this.province_id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (province_id != null ? province_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProvinceLocal)) {
            return false;
        }
        ProvinceLocal other = (ProvinceLocal) object;
        if ((this.province_id == null && other.province_id != null) || (this.province_id != null && !this.province_id.equals(other.province_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Province[ province_id=" + province_id + " ; " +
                "province_name=" + province_name + " ; " + 
                "x=" + x + " ; " + 
                "y=" + y + " ; "
                + " ]";
    }

    /**
     * @return the province_name
     */
    public String getProvince_name() {
        return province_name;
    }

    /**
     * @param province_name the province_name to set
     */
    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    /**
     * @return the x
     */
    public Float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Float y) {
        this.y = y;
    }

    /*
     * @return the neighbors
     *
    public List<Province> getNeighbors() {
        return neighbors;
    }

    /**
     * @param neighbors the neighbors to set
     *
    public void setNeighbors(List<Province> neighbors) {
        this.neighbors = neighbors;
    }*/
    
}
