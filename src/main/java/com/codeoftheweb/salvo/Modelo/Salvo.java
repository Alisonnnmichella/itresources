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
    private int turn;
    @ElementCollection
    private List<String> salvoLocations;

    public Salvo(int turnNumber,GamePlayer gamePlayer, List<String> locations) {
        this.gamePlayer = gamePlayer;
        this.turn = turnNumber;
        this.salvoLocations = locations;
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

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<String> getSalvoLocations() {
        return salvoLocations;
    }

    public void setSalvoLocations(List<String> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }

    public Map<String,Object> getDto(){
        Map<String,Object> dto= new HashMap<>();
        dto.put("turn",this.turn);
        dto.put("player",this.gamePlayer.getPlayer().getId());
        dto.put("locations",this.salvoLocations);
        return dto;
    }
}
