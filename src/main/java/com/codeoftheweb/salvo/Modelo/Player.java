package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;
    @OneToMany(mappedBy = "player",fetch =FetchType.EAGER )
    private Set<GamePlayer> gamePlayerSet;


    public Player() {
    }

    public Player(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String,Object> getDTO(){
        Map<String,Object> dto= new HashMap<>();
        dto.put("id",this.id);
        dto.put("email",this.userName);

        return dto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<GamePlayer> getGamePlayerSet() {
        return gamePlayerSet;
    }

    public void setGamePlayerList(Set<GamePlayer> gamePlayerSet) {
        this.gamePlayerSet = gamePlayerSet;
    }


}
