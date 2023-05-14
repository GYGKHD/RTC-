package com.gygk.fightking.executor;

import com.gygk.fightking.pojo.Warp;
import com.gygk.fightking.util.FileUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class WarpCommandExecutor implements CommandExecutor {
    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("warp")){

            if(sender instanceof Player){
                Player player = (Player) sender;
                String uri = String.valueOf(config.getDataFolder()) + "\\Warp.json";
                List<Warp> warps = FileUtil.readWarpFiles(uri);
                for (int i = 0; i < warps.size(); i++) {
                    if(warps.get(i).getName().equals(args[0])){
                        List<Integer> xyz = warps.get(i).getXyz();
//                        player.teleport();
                        Location location = new Location(player.getWorld(), xyz.get(0), xyz.get(1), xyz.get(2));
                        player.teleport(location);
                    }
                }
            }
        }


        return false;
    }
}
