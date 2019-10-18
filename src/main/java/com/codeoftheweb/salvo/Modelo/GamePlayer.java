package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class GamePlayer {
   @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;
    @OneToMany(mappedBy = "gamePlayer",fetch =FetchType.EAGER )
     Set<Ship> shipSet;
     public GamePlayer() {
    }
    LocalDateTime created;
    @OneToMany(mappedBy = "gamePlayer",fetch =FetchType.EAGER )
    Set<Salvo> salvoSet;


    public Set<Salvo> getSalvoSet() {
        return salvoSet;
    }

    public void setSalvoSet(Set<Salvo> salvoSet) {
        this.salvoSet = salvoSet;
    }

    public GamePlayer(Game game, Player player, LocalDateTime id) {
        this.game = game;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map<String,Object> makeDTO(){
        Map <String,Object> dto= new HashMap<>();
        dto.put("id",getId());
        dto.put("player",player.getDTO());
        return dto;

    }

    public Set<Ship> getShipSet() {
        return shipSet;
    }

    public void setShipSet(Set<Ship> shipSet) {
        this.shipSet = shipSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
