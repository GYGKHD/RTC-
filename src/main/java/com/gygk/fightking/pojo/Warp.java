package com.gygk.fightking.pojo;

import java.util.List;

public class Warp {
    private String name;
    private List<Integer> xyz;

    public Warp(String name, List<Integer> xyz) {
        this.name = name;
        this.xyz = xyz;
    }

    public Warp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getXyz() {
        return xyz;
    }

    public void setXyz(List<Integer> xyz) {
        this.xyz = xyz;
    }
}
