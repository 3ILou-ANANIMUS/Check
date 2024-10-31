package org.ru.scheck.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.ru.scheck.SCheck;
import org.ru.scheck.Utils.Cheater.CheatPlayer;
import org.ru.scheck.Utils.ConfigUtils;

public class ChatListener implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e){
        if (CheatPlayer.isCheck(e.getPlayer())){
            ConfigUtils config = new ConfigUtils(SCheck.getInstance);
            SCheck.getListCheatMod().get(e.getPlayer()).sendMessage(config.getString("settings.disableChat.sendModer").replace("%msg%", e.getMessage()));
            e.getPlayer().sendMessage(config.getString("settings.disableChat.text"));
            e.setCancelled(true);
        }
    }

}
