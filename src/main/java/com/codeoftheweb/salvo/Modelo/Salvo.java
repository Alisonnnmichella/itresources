package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne
    @JoinColumn(name="gamePlayerId")
    private GamePlayer gamePlayer;
    private int turnNumber;
    @ElementCollection
    private List<String> locations;

    public Salvo(int turnNumber,GamePlayer gamePlayer, List<String> locations) {
        this.gamePlayer = gamePlayer;
        this.turnNumber = turnNumber;
        this.locations = locations;
    }

    public Salvo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Map<String,Object> getDto(){
        Map<String,Object> dto= new HashMap<>();
        dto.put("turn",this.turnNumber);
        dto.put("player",this.gamePlayer.getPlayer().getId());
        dto.put("locations",this.locations);
        return dto;
    }
}
