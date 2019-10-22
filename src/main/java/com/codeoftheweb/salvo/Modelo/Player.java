package com.codeoftheweb.salvo.Modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;
    private String email;
    @OneToMany(mappedBy = "player",fetch =FetchType.EAGER )
    private Set<GamePlayer> gamePlayerSet;

    @OneToMany(mappedBy = "player",fetch =FetchType.EAGER )
    private Set<Score>  scores;


    public Player() {
    }

    public Player(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<GamePlayer> getGamePlayerSet() {
        return gamePlayerSet;
    }

    public void setGamePlayerSet(Set<GamePlayer> gamePlayerSet) {
        this.gamePlayerSet = gamePlayerSet;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Map<String,Object> getDTO(){
        Map<String,Object> dto= new HashMap<>();
        dto.put("id",this.id);
        dto.put("email",this.userName);

        return dto;
    }
    public Score getScore(Game game){
        Optional<Score> score=
        this.getScores().stream().filter(score1 -> score1.getGame().getId()==game.getId()).findFirst();
        if(!score.isPresent())
            return null;
        return  score.get();
    }
//    a total score
    public double totalScore(){
    return  this.getScores().stream().mapToDouble(score -> score.getScore()).sum();
    }

//    a total number of wins

    public double totalScoreValues(ScoreValue scoreValue){
        return  this.getScores().stream().filter(score -> score.getScoreValue() ==scoreValue).count();
    }

}
