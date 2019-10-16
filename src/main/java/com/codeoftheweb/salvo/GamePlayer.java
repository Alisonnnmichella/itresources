package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public GamePlayer() {
    }

    public GamePlayer(Game game, Player player) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
