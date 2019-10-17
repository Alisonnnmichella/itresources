package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;


@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;
    @ElementCollection
    @Column(name="shipLocation")
    private List<String> locations = new ArrayList<>();
    private String type;

    public Ship() {
    }

    public Ship(GamePlayer gamePlayer, List<String> locations, String type) {
        this.gamePlayer = gamePlayer;
        this.locations = locations;
        this.type = type;
    }

    public Ship(GamePlayer gamePlayer, String tipoDeBarco) {
        this.gamePlayer = gamePlayer;
        this.type = type;
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Map<String, Object> getDTO(){
        Map <String,Object> dto= new HashMap<>();
        dto.put("Type",this.type);
        dto.put("Locations",this.locations);
        return dto;
    }
}
