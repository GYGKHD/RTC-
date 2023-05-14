package com.gygk.fightking.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class PlayerDestory implements Listener {
    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    @EventHandler
    public void PlayerDestory(BlockBreakEvent event){
        Player player = event.getPlayer();
        event.getBlock();
        if(!player.isOp()){
            event.setCancelled(true);
        }
        if(player.isOp() && !config.getConfig().getBoolean("adminblock")){
            event.setCancelled(true);
        }

    }
}
