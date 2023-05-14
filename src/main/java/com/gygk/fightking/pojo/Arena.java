package com.gygk.fightking.pojo;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Arena {
    private String name;
    private int id;
    private List<String> equipment;
    private List<String> inventory;
    private List<Integer> firstLocation;
    private List<Integer> secondLocation;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public List<Integer> getFirstLocation() {
        return firstLocation;
    }

    public void setFirstLocation(List<Integer> firstLocation) {
        this.firstLocation = firstLocation;
    }

    public List<Integer> getSecondLocation() {
        return secondLocation;
    }

    public void setSecondLocation(List<Integer> secondLocation) {
        this.secondLocation = secondLocation;
    }

    public Arena() {
    }

    public Arena(String name, int id, List<String> equipment, List<String> inventory, List<Integer> firstLocation, List<Integer> secondLocation) {
        this.name = name;
        this.id = id;
        this.equipment = equipment;
        this.inventory = inventory;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
    }
}
