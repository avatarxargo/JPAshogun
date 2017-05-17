/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Transactions.findByType", query = "SELECT t FROM Transactions t WHERE t.type = :type"),
    @NamedQuery(name = "Transactions.findByDay", query = "SELECT t FROM Transactions t WHERE t.day = :day"),
    @NamedQuery(name = "Transactions.findByProvince1", query = "SELECT t FROM Transactions t WHERE t.province1 = :province1"),
    @NamedQuery(name = "Transactions.findByProvince2", query = "SELECT t FROM Transactions t WHERE t.province2 = :province2"),
    @NamedQuery(name = "Transactions.findByCount", query = "SELECT t FROM Transactions t WHERE t.count = :count"),
    @NamedQuery(name = "Transactions.findByBuilding", query = "SELECT t FROM Transactions t WHERE t.building = :building")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "type")
    private String type;
    @Column(name = "day")
    private Integer day;
    @Column(name = "province1")
    private Integer province1;
    @Column(name = "province2")
    private Integer province2;
    @Column(name = "count")
    private Integer count;
    @Column(name = "building")
    private Integer building;

    public Transactions() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getProvince1() {
        return province1;
    }

    public void setProvince1(Integer province1) {
        this.province1 = province1;
    }

    public Integer getProvince2() {
        return province2;
    }

    public void setProvince2(Integer province2) {
        this.province2 = province2;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }
    
}
