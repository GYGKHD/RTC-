package com.gygk.fightking.pojo;

public class ArenaIv {
    private String name;
    private int amount;

    public ArenaIv(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public ArenaIv() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
