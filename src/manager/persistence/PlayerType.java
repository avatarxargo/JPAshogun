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
@Table(name = "player_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlayerType.findAll", query = "SELECT p FROM PlayerType p"),
    @NamedQuery(name = "PlayerType.findByIdPlayerType", query = "SELECT p FROM PlayerType p WHERE p.idPlayerType = :idPlayerType"),
    @NamedQuery(name = "PlayerType.findByTypeName", query = "SELECT p FROM PlayerType p WHERE p.typeName = :typeName")})
public class PlayerType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_player_type")
    private Integer idPlayerType;
    @Basic(optional = false)
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playerTypeId")
    private Collection<Player> playerCollection;

    public PlayerType() {
    }

    public PlayerType(Integer idPlayerType) {
        this.idPlayerType = idPlayerType;
    }

    public PlayerType(Integer idPlayerType, String typeName) {
        this.idPlayerType = idPlayerType;
        this.typeName = typeName;
    }

    public Integer getIdPlayerType() {
        return idPlayerType;
    }

    public void setIdPlayerType(Integer idPlayerType) {
        this.idPlayerType = idPlayerType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    public void setPlayerCollection(Collection<Player> playerCollection) {
        this.playerCollection = playerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlayerType != null ? idPlayerType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlayerType)) {
            return false;
        }
        PlayerType other = (PlayerType) object;
        if ((this.idPlayerType == null && other.idPlayerType != null) || (this.idPlayerType != null && !this.idPlayerType.equals(other.idPlayerType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.PlayerType[ idPlayerType=" + idPlayerType + " ]";
    }
    
}
