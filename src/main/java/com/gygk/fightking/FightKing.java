package com.gygk.fightking;

import com.google.gson.Gson;
import com.gygk.fightking.event.PlayerDestory;
import com.gygk.fightking.event.PlayerDie;
import com.gygk.fightking.event.PlayerDropItem;
import com.gygk.fightking.event.PlayerJoin;
import com.gygk.fightking.executor.ArenaCommandExecutor;
import com.gygk.fightking.executor.ChangeAdminBlockExecutor;
import com.gygk.fightking.executor.SetWarpCommandExecutor;
import com.gygk.fightking.executor.WarpCommandExecutor;
import com.gygk.fightking.pojo.Arena;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.security.PrivateKey;
import java.util.logging.Logger;

public final class FightKing extends JavaPlugin {



    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerDie(),this);
        getServer().getPluginManager().registerEvents(new PlayerDestory(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(),this);
        getCommand("Arena").setExecutor(new ArenaCommandExecutor());
        getCommand("adminblock").setExecutor(new ChangeAdminBlockExecutor());
        getCommand("Warp").setExecutor(new WarpCommandExecutor());
        getCommand("SetWarp").setExecutor(new SetWarpCommandExecutor());
        saveDefaultConfig();
        FileConfiguration config = getConfig();

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
