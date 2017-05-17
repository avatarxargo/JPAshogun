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
@Table(name = "player")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findByIdPlayer", query = "SELECT p FROM Player p WHERE p.idPlayer = :idPlayer"),
    @NamedQuery(name = "Player.findByName", query = "SELECT p FROM Player p WHERE p.name = :name"),
    @NamedQuery(name = "Player.findByLogin", query = "SELECT p FROM Player p WHERE p.login = :login"),
    @NamedQuery(name = "Player.findByPassword", query = "SELECT p FROM Player p WHERE p.password = :password")})
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_player")
    private Integer idPlayer;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "clan_id", referencedColumnName = "id_clan")
    @ManyToOne
    private Clan clanId;
    @JoinColumn(name = "player_type_id", referencedColumnName = "id_player_type")
    @ManyToOne(optional = false)
    private PlayerType playerTypeId;

    public Player() {
    }

    public Player(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Player(Integer idPlayer, String name, String login, String password) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Clan getClanId() {
        return clanId;
    }

    public void setClanId(Clan clanId) {
        this.clanId = clanId;
    }

    public PlayerType getPlayerTypeId() {
        return playerTypeId;
    }

    public void setPlayerTypeId(PlayerType playerTypeId) {
        this.playerTypeId = playerTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlayer != null ? idPlayer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.idPlayer == null && other.idPlayer != null) || (this.idPlayer != null && !this.idPlayer.equals(other.idPlayer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manager.persistence.Player[ idPlayer=" + idPlayer + " ]";
    }
    
}
