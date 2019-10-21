package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne
    Game game;
    @ManyToOne
    Player player;
    LocalDateTime finishDate;
    @Transient
    ScoreValue scoreValue;
    double score;

    public Score(Game game, Player player, LocalDateTime finishDate, ScoreValue scoreValue) {
        this.game = game;
        this.player = player;
        this.finishDate = finishDate;
        this.scoreValue = scoreValue;
        this.score = scoreValue.getScore();
    }

    public Score() {
    }

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

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public ScoreValue getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(ScoreValue scoreValue) {
        this.scoreValue = scoreValue;
    }

    public double getScore() {
        return score;
    }

    public Map<String, Object> makeDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("player",this.player.getId());
        dto.put("score",this.score);
        dto.put("finishDate",this.finishDate);
        return dto;
    }

}
