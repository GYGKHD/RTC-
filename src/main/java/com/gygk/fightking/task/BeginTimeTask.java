package com.gygk.fightking.task;

import com.gygk.fightking.pojo.BallPlayer;
import com.gygk.fightking.util.FileUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.List;

import static org.bukkit.Instrument.XYLOPHONE;

public class BeginTimeTask extends BukkitRunnable {

    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    @Override
    public void run() {
        int time = 5;
        for (int j = 0; j < 6; j++) {
            String urip = String.valueOf(config.getDataFolder()) + "\\player.json";
            Server server = Bukkit.getServer();
            Collection<? extends Player> onlinePlayers = server.getOnlinePlayers();
            Object[] objects = onlinePlayers.toArray();
            BallPlayer ballPlayer = FileUtil.readBPFiles(urip);
            for (int i = 0; i < objects.length; i++) {
                Player player = (Player) objects[i];
                player.sendTitle("游戏将在"+time+"s后开始",
                        "请"+ ballPlayer.getPlayer1()+" " +ballPlayer.getPlayer2()+"做好准备",
                        0,
                        20,
                        15);
                if(j == 5){
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT,.5f,.5f);
                } else {
                    player.playNote(player.getLocation(), Instrument.PLING,new Note(12));
                }

//                player.playNote(player.getLocation(),XYLOPHONE,new Note(15));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }


        time--;
    }
}
