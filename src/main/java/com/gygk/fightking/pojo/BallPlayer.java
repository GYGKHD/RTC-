package com.gygk.fightking.pojo;

public class BallPlayer {
    private String player1;
    private String player2;

    public BallPlayer(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public BallPlayer() {
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
}
