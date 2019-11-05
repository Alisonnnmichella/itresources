package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Ship> shipSet = new HashSet<>();

    public GamePlayer() {
    }

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Salvo> salvoSet = new HashSet<>();

    LocalDateTime created;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Ship> getShipSet() {

        return shipSet;
    }

    public void setShipSet(Set<Ship> shipSet) {
        this.shipSet = shipSet;
    }

    public Set<Salvo> getSalvoSet() {
        return salvoSet;
    }

    public void º(Set<Salvo> salvoSet) {
        this.salvoSet = salvoSet;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    public GamePlayer(Game game, Player player, LocalDateTime localDateTime) {
        this.game = game;
        this.player = player;
        this.created = localDateTime;
    }

    public Map<String, Object> idplayerdto() {
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", this.getId());
        dto.put("player", player.getDTO());
        return dto;

    }

    public Score getScore() {
        return getPlayer().getScore(this.game);
    }

    public GamePlayer getOpponent() {
        return this.getGame().getGamePlayers()
                .stream()
                .filter(gamePlayer1 -> gamePlayer1.getPlayer().getId() != this.getPlayer().getId())
                .findFirst()
                .orElse(new GamePlayer());
    }


    public List<String> getLocationsShips() {
        return this.getShipSet().stream().map(ship -> ship.getLocations())
                .flatMap(strings -> strings.stream())
                .collect(Collectors.toList());
    }


    public List<String> getHits(int turno) {
        Optional<Salvo> salvo = getOpponent().getSalvoSet().stream()
                .filter(salvo1 -> salvo1.getTurn() == turno).findFirst();
        if (!salvo.isPresent())
            return new ArrayList<>();
        List<String> locationsSalvo = salvo.get().getSalvoLocations();

        return this.getLocationsShips().stream()
                .filter(posicion -> locationsSalvo.stream()
                        .anyMatch(salvoEnemigo1 -> salvoEnemigo1.equals(posicion)))
                .collect(Collectors.toList());
    }

    public Map<String, Object> getDamages(int turno) {
        HashMap<String, Object> dto = new LinkedHashMap<>();
        List<String> hits = getHits(turno);
        getShipSet().forEach(ship -> dto.put(ship.getType() + "Hits", ship.getHits(hits).size()));
        getShipSet().forEach(ship -> dto.put(ship.getType(), ship.getHitsRecibidos().size()));
        return dto;
    }

    public List<Map<String, Object>> dtoHitsAllTurns() {
        ArrayList<Map<String, Object>> hitsTotales = new ArrayList<>();
        int turno = 1;
        while (turno <= salvoSet.size()) {
            HashMap<String, Object> dto = new LinkedHashMap<>();
            List<String> hits = getHits(turno);
            dto.put("turn", turno);
            dto.put("hitLocations", hits);
            dto.put("damages", this.getDamages(turno));
            dto.put("missed", 5 - hits.size());
            hitsTotales.add(dto);
            turno++;
        }
        return hitsTotales;
    }


    public void setSalvoSet(Set<Salvo> salvoSet) {
        this.salvoSet = salvoSet;
    }

    public int countAllSinkedShips() {
        return shipSet.stream().filter(ship -> ship.isSink()).collect(Collectors.toList()).size();
    }


    public String getState() {
        if (getShipSet().isEmpty())
            return "PLACESHIPS";
        if (game.getGamePlayers().size() != 2)
            return "WAITINGFOROPP";
        if (getOpponent().getShipSet().isEmpty())
            return "WAIT";
        if (countAllSinkedShips() == getOpponent().countAllSinkedShips() && countAllSinkedShips() == shipSet.size())
            return "TIE";
        if (getShipSet().stream().allMatch(ship -> ship.isSink()))
            return "LOST";
        if (getOpponent().getShipSet().stream().allMatch(ship -> ship.isSink()))
            return "WON";
        if (getSalvoSet().size() <= getOpponent().getSalvoSet().size())
            return "PLAY";
        if (getSalvoSet().size() > getOpponent().getSalvoSet().size())
            return "WAIT";
        return "WAIT";
    }


}



