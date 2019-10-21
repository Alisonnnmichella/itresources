package com.codeoftheweb.salvo.Modelo;

public enum ScoreValue {
    WIN(1.0),LOST(0.0),TIED(0.5);

    private double score;

    private  ScoreValue(double score){
        this.score=score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
