package com.gygk.fightking.task;

import com.gygk.fightking.pojo.Arena;
import com.gygk.fightking.pojo.BallPlayer;
import com.gygk.fightking.util.FileUtil;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class InitGameTask extends BukkitRunnable {
    Plugin config = com.gygk.fightking.FightKing.getPlugin(com.gygk.fightking.FightKing.class);
    private Arena arena = new Arena();
    private Player player1;
    private Player player2;
    private World world;

    @Override
    public void run() {
        String uri = String.valueOf(config.getDataFolder()) + "\\Arena.json";
        String urip = String.valueOf(config.getDataFolder()) + "\\player.json";
        List<Arena> arenas = FileUtil.readArenaFiles(uri);
        Random random = new Random();
        int i1 = random.nextInt(arenas.size());
        arena = arenas.get(i1);
        BallPlayer ballPlayer = FileUtil.readBPFiles(urip);
//        for (int i = 0; i < arenas.size(); i++) {
//            if(arenas.get(i).getName().equals("road")){
//                arena = arenas.get(i);
//            }
//        }
        Server server = Bukkit.getServer();
        Collection<? extends Player> onlinePlayers = server.getOnlinePlayers();
        Object[] objects = onlinePlayers.toArray();
//        List<Player> players = new ArrayList<>();
//        for (int i = 0; i < objects.length; i++) {
//            players.add((Player) objects[i]) ;
//
//        }
        player1 = Bukkit.getPlayer(ballPlayer.getPlayer1());
        player2 = Bukkit.getPlayer(ballPlayer.getPlayer2());
//        int player1Int = random.nextInt(players.size());
//        player1 = players.get(player1Int);
//        players.remove(player1Int);
//        int player2Int = random.nextInt(players.size() - 1);
//        player2 = players.get(player2Int);
//        players.clear();
        Location location1 = new Location(player1.getWorld(), arena.getFirstLocation().get(0), arena.getFirstLocation().get(1), arena.getFirstLocation().get(2));
        Location location2 = new Location(player1.getWorld(), arena.getSecondLocation().get(0), arena.getSecondLocation().get(1), arena.getSecondLocation().get(2));

        world = player1.getWorld();

        iniArena();

        for (int i = 0; i < objects.length; i++) {
            Player player = (Player) objects[i];
            int i2 = random.nextInt(2);
            if (i2 == 0) {
                player.teleport(location1);
            } else {
                player.teleport(location2);
            }

            player.setGameMode(GameMode.SPECTATOR);
        }


        PotionEffect potionEffect = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 1);
        player1.setSaturation(20);
        player1.setHealth(20);
        player1.setFoodLevel(20);
        player1.addPotionEffect(potionEffect);

        player2.setSaturation(20);
        player2.setHealth(20);
        player2.setFoodLevel(20);
        player2.addPotionEffect(potionEffect);

        player1.teleport(location1);
        player2.teleport(location2);

        player2.setGameMode(GameMode.ADVENTURE);
        player1.setGameMode(GameMode.ADVENTURE);

        World world = player1.getWorld();
        world.setSpawnLocation(location1);

        PlayerInventory inventory1 = player1.getInventory();
        PlayerInventory inventory2 = player2.getInventory();

        inventory1.clear();
        inventory2.clear();
        List<String> inventory = arena.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            String[] split = inventory.get(i).split("/");
            int amount = Integer.parseInt(split[1]);
            if (arena.getName().equals("road") || arena.getName().equals("garden")) {
                if (split[0].equals("TROPICAL_FISH")) {
                    ItemStack itemStack = new ItemStack(Material.TROPICAL_FISH, 1);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.GOLD + "海皇");
                    itemMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);

                    itemStack.setItemMeta(itemMeta);
                    inventory1.addItem(itemStack);
                    inventory2.addItem(itemStack);
                    continue;
                }
                if (split[0].equals("BOW")) {
                    ItemStack itemStack1 = new ItemStack(Material.BOW, 1);
                    ItemMeta itemMeta1 = itemStack1.getItemMeta();
                    itemMeta1.setDisplayName(ChatColor.GOLD + "若水");
                    itemMeta1.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
                    itemStack1.setItemMeta(itemMeta1);
                    inventory1.addItem(itemStack1);
                    inventory2.addItem(itemStack1);
                    continue;
                }
            }


            inventory1.addItem(new ItemStack(Material.getMaterial(split[0]), amount));
            inventory2.addItem(new ItemStack(Material.getMaterial(split[0]), amount));
        }
        List<String> equipment = arena.getEquipment();
        inventory1.setBoots(new ItemStack(Material.getMaterial(equipment.get(0))));
        inventory1.setLeggings(new ItemStack(Material.getMaterial(equipment.get(1))));
        inventory1.setChestplate(new ItemStack(Material.getMaterial(equipment.get(2))));
        inventory1.setHelmet(new ItemStack(Material.getMaterial(equipment.get(3))));

        inventory2.setBoots(new ItemStack(Material.getMaterial(equipment.get(0))));
        inventory2.setLeggings(new ItemStack(Material.getMaterial(equipment.get(1))));
        inventory2.setChestplate(new ItemStack(Material.getMaterial(equipment.get(2))));
        inventory2.setHelmet(new ItemStack(Material.getMaterial(equipment.get(3))));


    }

    private void iniArena() {
        List<Entity> entities = world.getEntities();
        for (int i = 0; i < entities.size(); i++) {
            if (String.valueOf(entities.get(i).getType()) == "ARROW" || String.valueOf(entities.get(i).getType()) == "DROPPED_ITEM") {
                entities.get(i).remove();

            }
        }
    }
}