package com.gygk.fightking.executor;

import com.gygk.fightking.pojo.Arena;
import com.gygk.fightking.pojo.BallPlayer;
import com.gygk.fightking.task.BeginTimeTask;
import com.gygk.fightking.task.GetPlayerTask;
import com.gygk.fightking.task.InitGameTask;
import com.gygk.fightking.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class ArenaCommandExecutor implements CommandExecutor{
    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("arena")){

            if(sender instanceof Player){
                Player player = (Player) sender;

                if(args[0].equalsIgnoreCase("create") && args[1] != null ){
                    createArena(player,args[1]);
                }

                if(args[0].equalsIgnoreCase("setlocation") && args[1] != null ){

                    setLocation(player,args[1],args[2]);
                }
                if(args[0].equalsIgnoreCase("query") && args[1] != null ){

                    queryArena(player,args[1]);
                }
                if(args[0].equalsIgnoreCase("sp") && args[1] != null ){
                    setPlayer(args[1],args[2]);
                }
                if(args[0].equalsIgnoreCase("spq")){
                    queryPlayer(player);
                }

                if(args[0].equalsIgnoreCase("setcheat")){
                    setCheat(player,args[1]);
                }
                if(args[0].equalsIgnoreCase("getcheat")){
                    getCheat(player,args[1]);
                }
                if(args[0].equalsIgnoreCase("start")){
                    BeginTimeTask testTask = new BeginTimeTask();
                    InitGameTask initGameTask = new InitGameTask();
                    BukkitTask bukkitTask = testTask.runTaskAsynchronously(config);
                    initGameTask.runTaskLater(config,100);
                }
                if(args[0].equalsIgnoreCase("sstart")){
                    BeginTimeTask testTask = new BeginTimeTask();
                    InitGameTask initGameTask = new InitGameTask();

                    GetPlayerTask getPlayerTask = new GetPlayerTask(player);
                    getPlayerTask.runTask(config);
                    BukkitTask bukkitTask = testTask.runTaskAsynchronously(config);
                    initGameTask.runTaskLater(config,100);
                }
//                if(args[0].equalsIgnoreCase("clear")){
//                    World world = player.getWorld();
//                    List<Entity> entities = world.getEntities();
//
//                    for (int i = 0; i < entities.size(); i++) {
//                        if(String.valueOf(entities.get(i).getType()) == "ARROW"){
//                            entities.get(i).remove();
//
//                        }
//
//                    }
//                }
            }
        }



        return false;
    }

    private void getCheat(Player player, String name) {
        String uri = String.valueOf(config.getDataFolder()) + "\\Arena.json";
        PlayerInventory inventory = player.getInventory();
        List<Arena> arenas = FileUtil.readArenaFiles(uri);
        for (int i = 0; i < arenas.size(); i++) {

            if(arenas.get(i).getName().equals(name)){

                List<String> inventory1 = arenas.get(i).getInventory();
                for (int j = 0; j < inventory1.size(); j++) {
                    String[] split = inventory1.get(j).split("/");
                    int amount = Integer.parseInt(split[1]);
                    inventory.addItem(new ItemStack(Material.getMaterial(split[0]),amount));
                }
                return;
            }
        }
    }

    private void setCheat(Player player,String name) {
        String uri = String.valueOf(config.getDataFolder()) + "\\Arena.json";
        PlayerInventory inventory = player.getInventory();
//        inventory.addItem(new ItemStack(Material.ACACIA_BOAT,1));

        ItemStack[] contents = inventory.getStorageContents();
        ItemStack[] armorContents = inventory.getArmorContents();
        List<String> strings = new ArrayList<>();
        List<String> strings1 = new ArrayList<>();
//        player.sendMessage(contents[1].toString());
        for (int i = 0; i < contents.length ; i++) {
            if(contents[i] != null){
//                player.sendMessage(String.valueOf(contents[i].getType()));
                strings.add(String.valueOf(contents[i].getType()) + "/" + contents[i].getAmount());
            }
        }
        player.sendMessage(String.valueOf(armorContents.length));
        for (int i = 0; i < armorContents.length; i++) {
            if(armorContents[i] != null){
                player.sendMessage(String.valueOf(armorContents[i].getType()));
                strings1.add(String.valueOf(armorContents[i].getType()));
            }
        }

        List<Arena> arenas = FileUtil.readArenaFiles(uri);
        for (int i = 0; i < arenas.size(); i++) {

            if(arenas.get(i).getName().equals(name)){
                arenas.get(i).setInventory(strings);
                arenas.get(i).setEquipment(strings1);
                FileUtil.writeArenaFiles(uri,arenas);
                player.sendMessage("添加成功");
                return;
            }
        }
        player.sendMessage("添加失败");


    }

    private void queryPlayer(Player player) {
        String uri = String.valueOf(config.getDataFolder()) + "\\Player.json";
        BallPlayer ballPlayer = FileUtil.readBPFiles(uri);
        player.sendMessage("玩家列表");
        player.sendMessage("Player1: " + ballPlayer.getPlayer1());
        player.sendMessage("Player2: " + ballPlayer.getPlayer2());
    }

    private void setPlayer(String player,String name) {
        String uri = String.valueOf(config.getDataFolder()) + "\\Player.json";

        if(player.equals("player1")){
            BallPlayer ballPlayer = FileUtil.readBPFiles(uri);
            ballPlayer.setPlayer1(name);
            FileUtil.writeBPFiles(uri,ballPlayer);

        } else if(player.equals("player2")){
            BallPlayer ballPlayer = FileUtil.readBPFiles(uri);
            ballPlayer.setPlayer2(name);
            FileUtil.writeBPFiles(uri,ballPlayer);
        }

    }

    private void queryArena(Player player,String name) {
        String uri = String.valueOf(config.getDataFolder()) + "\\Arena.json";
        List<Arena> arenas = FileUtil.readArenaFiles(uri);
        for (int i = 0; i < arenas.size() ;i++) {
            if(arenas.get(i).getName().equals(name)){
                player.sendMessage("名称 " + arenas.get(i).getName()  );
                player.sendMessage("ID " + arenas.get(i).getId() );
                player.sendMessage("第一个位置 " + arenas.get(i).getFirstLocation() );
                player.sendMessage("第二个位置 " + arenas.get(i).getSecondLocation());

                return;
            }
        }
    }

    private void setLocation(Player player, String name, String l) {
        String uri = String.valueOf(config.getDataFolder()) + "\\Arena.json";
        List<Arena> arenas = FileUtil.readArenaFiles(uri);
        if(l.equals("1")){
            for (int i = 0; i < arenas.size(); i++) {
                if(arenas.get(i).getName().equals(name)){
                    List<Integer> list = new ArrayList<>();
                    Location location = player.getLocation();
                    list.add((int) location.getX());
                    list.add((int) location.getY());
                    list.add((int) location.getZ());
                    arenas.get(i).setFirstLocation(list);
                    FileUtil.writeArenaFiles(uri,arenas);
                    player.sendMessage("1号位置设置成功!");
                    return;
                }
            }
        } else if (l.equals("2")){
            for (int i = 0; i < arenas.size(); i++) {
                if(arenas.get(i).getName().equals(name)){
                    List<Integer> list = new ArrayList<>();
                    Location location = player.getLocation();
                    list.add((int) location.getX());
                    list.add((int) location.getY());
                    list.add((int) location.getZ());
                    arenas.get(i).setSecondLocation(list);
                    FileUtil.writeArenaFiles(uri,arenas);
                    player.sendMessage("2号位置设置成功!");
                    return;
                }
            }
        } else {
            player.sendMessage("位置无效!");
        }
    }

    public void createArena(Player player,String name){

        String uri = String.valueOf(config.getDataFolder()) + "\\Arena.json";
        List<Arena> arenas = FileUtil.readArenaFiles(uri);
        for (int i = 0; i < arenas.size(); i++) {
            if (arenas.get(i).getName().equals(name)){
                player.sendMessage("你输入的名称重合了");
                return;
            }
        }
        Arena arena = new Arena();
        arena.setName(name);
//        System.out.println(arena);
        arenas.add(arena);
        FileUtil.writeArenaFiles(uri,arenas);


    }
}
