package org.ru.scheck.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.ru.scheck.SCheck;
import org.ru.scheck.Utils.Cheater.CheatPlayer;
import org.ru.scheck.Utils.ConfigUtils;

public class PlayerListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            if (CheatPlayer.isCheck((Player) e.getEntity())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInv(InventoryClickEvent e){
        if (CheatPlayer.isCheck((Player) e.getWhoClicked())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (CheatPlayer.isCheck(e.getPlayer())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if (CheatPlayer.isCheck(e.getPlayer())) {
            ConfigUtils config = new ConfigUtils(SCheck.getInstance);
            if (config.getBoolean("settings.banPlayer.enable")){
                SCheck.listCheatMod.get(e.getPlayer()).performCommand(config.getString("settings.banPlayer.command").replace("%target%", e.getPlayer().getName()));
                SCheck.listCheatMod.get(e.getPlayer()).sendMessage(config.getString("settings.banPlayer.text"));
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().resetTitle();
    }
}
