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
    private Set<String> hitsRecibidos=hitsRecibidos= new HashSet<>();

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

    public List<String> getHits(List<String> locationsSalvoesEnemigosPorTurno){
        List<String> locations= this.getLocations().stream()
                .filter(location->locationsSalvoesEnemigosPorTurno.stream()
                        .anyMatch(s -> s.equals(location))).collect(Collectors.toList());
        hitsRecibidos.addAll(locations);
        return locations;
    }

    public Set<String> getHitsRecibidos() {
        return hitsRecibidos;
    }

    public void setHitsRecibidos(Set<String> hitsRecibidos) {
        this.hitsRecibidos = hitsRecibidos;
    }
    public boolean isSink(){
        return hitsRecibidos.size()==locations.size();
    }

}
