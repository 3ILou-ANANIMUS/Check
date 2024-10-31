package org.ru.scheck.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.ru.scheck.SCheck;
import org.ru.scheck.Utils.Cheater.CheatPlayer;
import org.ru.scheck.Utils.ConfigUtils;

public class CmdListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        if (CheatPlayer.isCheck(e.getPlayer())){
            if (e.getMessage().startsWith("/mod")) {return;}
            ConfigUtils config = new ConfigUtils(SCheck.getInstance);
            SCheck.listCheatMod.get(e.getPlayer()).sendMessage(config.getString("settings.disableCommand.sendModer").replace("%command%", e.getMessage()));
            e.getPlayer().sendMessage(config.getString("settings.disableCommand.text"));
            e.setCancelled(true);
        }
    }
}
