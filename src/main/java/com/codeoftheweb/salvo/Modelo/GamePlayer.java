package com.codeoftheweb.salvo.Modelo;

import com.codeoftheweb.salvo.Repositories.ScoreRepository;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
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
    Set<Ship> shipSet;

    public GamePlayer() {
    }

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Salvo> salvoSet;

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

    public Map<String, Object> makeDTO() {
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", this.getId());
        dto.put("player", player.getDTO());
        return dto;

    }

    public Score getScore() {
        return getPlayer().getScore(this.game);
    }

    public GamePlayer getEnemigo() {
        return this.getGame().getGamePlayers().stream().filter(gamePlayer -> gamePlayer.getPlayer().getId() != this.getId()).findFirst().orElse(null);
    }

    public List<String> getPosicionesBarco() {
        return this.getShipSet().stream().map(ship -> ship.getLocations()).flatMap(strings -> strings.stream()).collect(Collectors.toList());
    }

    public List<String> getPosicionesSalvoPorTurno(int turno) {
        Optional<Salvo> salvo = this.getSalvoSet().stream().filter(salvo1 -> salvo1.getTurnNumber() == turno).findFirst();
        if (salvo.isPresent())
            return salvo.get().getLocations();
        return new ArrayList<>();
    }

    public List<String> getPosicionesSalvoHastaNTurno(int turno) {
        List<String> posiciones = new ArrayList<>();
        while (turno > 0) {
            posiciones.addAll(getPosicionesSalvoPorTurno(turno));
            turno--;
        }
        return posiciones;
    }

    public List<String> getHits(List<String> posicionesSalvoEnemigo) {
        return this.getPosicionesBarco().stream().filter(posicion -> posicionesSalvoEnemigo.stream().anyMatch(salvoEnemigo1 -> salvoEnemigo1.equals(posicion))).collect(Collectors.toList());
    }

    public Map<String, Object> prueba(int turno) {

        HashMap<String, Object> dto = new LinkedHashMap<>();
        dto.put("enemigo", getEnemigo().getId());
        dto.put("ships", this.getPosicionesBarco());
        dto.put("info ships", this.getShipSet().stream().map(ship -> ship.getDTO()).collect(Collectors.toList()));
        dto.put("salvoes", this.getEnemigo().getPosicionesSalvoPorTurno(turno));
        List<String> hits = getHits(getEnemigo().getPosicionesSalvoPorTurno(turno));
        List<String> hitsAcumulativos = getHits(getEnemigo().getPosicionesSalvoHastaNTurno(turno));

        dto.put("hitsAcumulativo", hitsAcumulativos);
        dto.put("turn", turno);
        dto.put("hitLocations", hits);
        getShipSet().forEach(ship -> dto.put(ship.getType() + "Hits", ship.getHits(hits).size()));
        getShipSet().forEach(ship -> dto.put(ship.getType(), ship.getHits(hitsAcumulativos).size()));
        return dto;

    }


    public Map<String, Object> damages(int turno) {
        HashMap<String, Object> dto = new LinkedHashMap<>();
        List<String> hits = getHits(getEnemigo().getPosicionesSalvoPorTurno(turno));
        List<String> hitsAcumulativos = getHits(getEnemigo().getPosicionesSalvoHastaNTurno(turno));
        getShipSet().forEach(ship -> dto.put(ship.getType() + "Hits", ship.getHits(hits).size()));
        getShipSet().forEach(ship -> dto.put(ship.getType(), ship.getHits(hitsAcumulativos).size()));
        return dto;
    }

    public Map<String, Object> hitsPorTurno(int turno) {
        HashMap<String, Object> dto = new LinkedHashMap<>();
        List<String> hits = getHits(getEnemigo().getPosicionesSalvoPorTurno(turno));
        dto.put("turn", turno);
        dto.put("hitLocations", hits);
        dto.put("damages", this.damages(turno));
        dto.put("missed", 5 - hits.size());
        return dto;
    }



    public List<Map<String, Object>> hitsTodosLosTurnos() {
        ArrayList<Map<String, Object>> hitsTotales = new ArrayList<>();
        int i = 1;
        while (i <= salvoSet.size()) {
            hitsTotales.add(hitsPorTurno(i));
            i++;
        }
        return hitsTotales;
    }


    public void setSalvoSet(Set<Salvo> salvoSet) {
        this.salvoSet = salvoSet;
    }

}



