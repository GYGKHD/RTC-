package com.gygk.fightking.task;

import com.gygk.fightking.pojo.BallPlayer;
import com.gygk.fightking.util.FileUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.Random;

public class GetPlayerTask extends BukkitRunnable {
    private Player sender;

    public GetPlayerTask(Player sender) {
        this.sender = sender;
    }

    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    @Override
    public void run() {
        String urip = String.valueOf(config.getDataFolder()) + "\\player.json";
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        Random random = new Random();
        Object[] objects = onlinePlayers.toArray();
        int i1 = 0;
        int i2 = 0;
        while (i1 == i2){
            i1 = random.nextInt(objects.length);
            i2 = random.nextInt(objects.length);
        }
        BallPlayer ballPlayer = FileUtil.readBPFiles(urip);
        Player player1 = (Player) objects[i1];
        Player player2 = (Player) objects[i2];
        ballPlayer.setPlayer1(player1.getName());
        ballPlayer.setPlayer2(player2.getName());
        FileUtil.writeBPFiles(urip,ballPlayer);
    }
}
