package org.ru.scheck;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.ru.scheck.Commands.CheckCommand;
import org.ru.scheck.Commands.ModCommand;
import org.ru.scheck.Listener.ChatListener;
import org.ru.scheck.Listener.CmdListener;
import org.ru.scheck.Listener.PlayerListener;

import java.util.HashMap;
import java.util.Map;

public final class SCheck extends JavaPlugin {

    public static SCheck getInstance;

    @Getter
    public static Map<Player, Player> listCheatMod;

    public SCheck(){
        getInstance = this;
    }

    @Override
    public void onEnable() {
        getCommand("check").setExecutor(new CheckCommand());
        getCommand("mod").setExecutor(new ModCommand());

        if (this.getConfig().getBoolean("settings.disableCommand.enable")){
            getServer().getPluginManager().registerEvents(new CmdListener(), this);
        }
        if (this.getConfig().getBoolean("settings.disableChat.enable")){
            getServer().getPluginManager().registerEvents(new ChatListener(), this);
        }
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        listCheatMod = new HashMap<>();

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

    }
}
