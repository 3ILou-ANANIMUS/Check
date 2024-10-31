package org.ru.scheck.Utils.Cheater;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.C;
import org.ru.scheck.SCheck;
import org.ru.scheck.Utils.ConfigUtils;

import java.util.Map;

public class CheatPlayer {

    public Player target;
    public Player moder;
    private Location loc;

    public CheatPlayer(Player player, Player moder){
        this.target = player;
        this.moder = moder;
    }

    public void check() {
        if (!isCheck(target)) {
            SCheck.listCheatMod.put(target, moder);

            loc = target.getLocation();
            ConfigUtils config = new ConfigUtils(SCheck.getInstance);
            target.sendTitle(
                    config.getString("message.start.title"),
                    config.getString("message.start.subTitle"),
                    config.getInt("message.start.timeIn"),
                    config.getInt("message.start.stay"),
                    config.getInt("message.start.timeOut")
            );
            config.sendListMessages(config.getList("message.start.target"), target);

            new BukkitRunnable(){

                @Override
                public void run() {
                    if (isCheck(target) && target != null){
                        target.teleport(loc.getWorld().getHighestBlockAt((int) loc.getX(), (int) loc.getZ()).getLocation().add(0, 1, 0));
                    } else {
                        this.cancel();
                    }
                }
            }.runTaskTimer(SCheck.getInstance, 0, 0);
        }

    }

    public static boolean isCheck(Player player){
        return SCheck.getListCheatMod().containsKey(player);
    }

    public String removeCheck(){
        ConfigUtils config = new ConfigUtils(SCheck.getInstance);
        if (!SCheck.listCheatMod.get(target).equals(moder)){
            return config.getString("message.already");
        }
        SCheck.listCheatMod.remove(target, moder);
        target.resetTitle();
        return config.getString("message.removeCheck").replace("%target%", target.getName());
    }

}
