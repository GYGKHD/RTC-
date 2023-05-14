package com.gygk.fightking.event;

import com.gygk.fightking.pojo.BallPlayer;
import com.gygk.fightking.util.FileUtil;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import java.util.Collection;

public class PlayerDie implements Listener {
    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    @EventHandler
    public void onPlayerDie(EntityDeathEvent event){
        if(event.getEntity() instanceof Player && event.getEntity() != null){
            String uri = String.valueOf(config.getDataFolder()) + "\\Player.json";
            Player player = (Player) event.getEntity();
            BallPlayer ballPlayer = FileUtil.readBPFiles(uri);
            if(player.getName().equals(ballPlayer.getPlayer1()) || player.getName().equals(ballPlayer.getPlayer2())){
                String sPlayer = "";
                String dPlayer = "";
                if(player.getName().equals(ballPlayer.getPlayer1())){
                    sPlayer = ballPlayer.getPlayer2();
                    dPlayer = ballPlayer.getPlayer1();
                }
                if(player.getName().equals(ballPlayer.getPlayer2())){
                    sPlayer = ballPlayer.getPlayer1();
                    dPlayer = ballPlayer.getPlayer2();
                }
                Player ddplayer = Bukkit.getPlayer(dPlayer);
                Player ssplayer = Bukkit.getPlayer(sPlayer);
                World world = ddplayer.getWorld();
                world.strikeLightningEffect(ddplayer.getLocation());
//                ssplayer.setHealth(0);

                Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                Object[] objects = onlinePlayers.toArray();
                for (int i = 0; i < objects.length; i++) {
                    Player player1 = (Player) objects[i];
                    player1.sendTitle(sPlayer+"获胜","太厉害了");
                    player1.setGameMode(GameMode.SPECTATOR);

                    player1.sendMessage(ChatColor.GOLD+sPlayer+"获胜");
                }
            }
        }
    }
}
