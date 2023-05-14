package com.gygk.fightking.executor;

import com.gygk.fightking.pojo.Warp;
import com.gygk.fightking.util.FileUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SetWarpCommandExecutor implements CommandExecutor {
    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("setwarp") && args[0] != ""){

            if(sender instanceof Player){
                Player player = (Player) sender;
                String uri = String.valueOf(config.getDataFolder()) + "\\Warp.json";
                List<Warp> warps = FileUtil.readWarpFiles(uri);
                for (int i = 0; i < warps.size(); i++) {
                    if(warps.get(i).getName().equals(args[0])){


                        List<Integer> list = new ArrayList<>();
                        list.add((int) player.getLocation().getX());
                        list.add((int) player.getLocation().getY());
                        list.add((int) player.getLocation().getZ());
//                        player.getInventory()
                        warps.get(i).setXyz(list);
                        player.sendMessage("修改" + args[0] + "成功");
                        FileUtil.writeWarpFiles(uri,warps);
                        return false;
                    }
                }
                Warp warp = new Warp();
                warp.setName(args[0]);
                List<Integer> list = new ArrayList<>();
                list.add((int) player.getLocation().getX());
                list.add((int) player.getLocation().getY());
                list.add((int) player.getLocation().getZ());
                warp.setXyz(list);
                warps.add(warp);
                FileUtil.writeWarpFiles(uri,warps);
                player.sendMessage("添加" + args[0] + "成功");
            }
        } else {

        }
        return false;
    }
}
