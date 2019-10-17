package com.codeoftheweb.salvo;

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
    public Game(){
    }

    public Game(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDate() {
        return localDateTime;
    }

    public void setLocalDate(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Map<String, Object> makeCreationDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id",this.id);
        dto.put("created",this.localDateTime);


        return dto;

    }



    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}
