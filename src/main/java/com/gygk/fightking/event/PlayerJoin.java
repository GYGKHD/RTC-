package com.gygk.fightking.event;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class PlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!player.isOp()){
            player.setGameMode(GameMode.SPECTATOR);
        }
        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective("name-below");
        if(Objects.isNull(objective)){
            objective = scoreboard.registerNewObjective("name-below", Criteria.HEALTH, ChatColor.GREEN + "玩家血量");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setRenderType(RenderType.HEARTS);
        }
    }
    @EventHandler
    public void PlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(!player.isOp()){
            event.setCancelled(true);
            player.sendMessage("比赛期间不能说话");
        }
    }
}
