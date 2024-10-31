package org.ru.scheck.Utils;

import org.bukkit.entity.Player;
import org.ru.scheck.SCheck;

import java.util.ArrayList;
import java.util.List;

public class ConfigUtils {

    private SCheck plugin;

    public ConfigUtils(SCheck sCheck){
        this.plugin = sCheck;
    }

    public String getString(String path){
        return Color.translate(plugin.getConfig().getString(path));
    }

    public Integer getInt(String path){
        return plugin.getConfig().getInt(path);
    }

    public boolean getBoolean(String path){
        return plugin.getConfig().getBoolean(path);
    }

    public List<String> getList(String path){
        return plugin.getConfig().getStringList(path);
    }

    public void sendListMessages(List<String> path, Player player){
        for (String line : path){
            player.sendMessage(Color.translate(line));
        }
    }
}
