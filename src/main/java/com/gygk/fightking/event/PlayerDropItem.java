package com.gygk.fightking.event;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class PlayerDropItem implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerDropItemEvent event){

        Player player = event.getPlayer();
        if(!player.isOp()){
            player.sendMessage("禁止丢东西!");
            event.setCancelled(true);
        }

    }
}
