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
    @NamedQuery(name = "Simday.findByIdSimday", query = "SELECT s FROM Simday s WHERE s.idSimday = :idSimday")})
public class Simday implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_simday")
    private Integer idSimday;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "simdayIdSimday")
    private Collection<Transactions> transactionsCollection;

    public Simday() {
    }

    public Simday(Integer idSimday) {
        this.idSimday = idSimday;
    }

    public Integer getIdSimday() {
        return idSimday;
    }

    public void setIdSimday(Integer idSimday) {
        this.idSimday = idSimday;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
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
