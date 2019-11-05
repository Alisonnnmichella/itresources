package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    LocalDateTime localDateTime;

    @OneToMany(mappedBy = "game",fetch =FetchType.EAGER )
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game",fetch =FetchType.EAGER )
    private Set<Score> scores;


    public Game(){}
    public Game(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Map<String, Object> makeCreationDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id",this.id);
        dto.put("created",this.localDateTime);
        dto.put("gamePlayers",this.getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.idplayerdto()));
        dto.put("scores", this.getScores().stream().map(score -> score.makeDTO()));
        return dto;
    }
    public double getScore(){
        return this.scores.stream().mapToDouble(Score::getScore).sum();
    }

}
