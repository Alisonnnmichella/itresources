package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


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
    @Transient
    private ShipState shipState;

    public Ship() {
    }

    public Ship(GamePlayer gamePlayer, List<String> locations, String type) {
        this.gamePlayer = gamePlayer;
        this.locations = locations;
        this.type = type;
    }

    public Ship(GamePlayer gamePlayer, String type,List<String> locations) {
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
        Map <String,Object> dto= new LinkedHashMap<>();
        dto.put("type",this.type);
        dto.put("locations",this.locations);
        return dto;
    }
    public List<String> getHits(List<String> locationsSalvoesEnemigos){
         return   this.getLocations().stream().filter(location->locationsSalvoesEnemigos.stream().anyMatch(s -> s.equals(location))).collect(Collectors.toList());
    }
    public void sink(List<String> locationsSalvoesEnemigos){
       if( getHits(locationsSalvoesEnemigos).size() <= this.locations.size())
               setShipState(ShipState.NORMAL);
       setShipState(ShipState.SINK);

    }

    public ShipState getShipState() {
        return shipState;
    }

    public void setShipState(ShipState shipState) {
        this.shipState = shipState;
    }
}
