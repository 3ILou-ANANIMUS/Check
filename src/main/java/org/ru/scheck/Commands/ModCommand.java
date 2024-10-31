package org.ru.scheck.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.ru.scheck.SCheck;
import org.ru.scheck.Utils.Cheater.CheatPlayer;
import org.ru.scheck.Utils.ConfigUtils;

public class ModCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            Bukkit.getLogger().info("Только игрок может писать!");
            return true;
        }

        Player player = (Player) sender;

        if (SCheck.getListCheatMod().containsKey(player) && CheatPlayer.isCheck(player)){
            Player moder = SCheck.getListCheatMod().get(player);
            ConfigUtils config = new ConfigUtils(SCheck.getInstance);
            StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(" " + arg);
            }
            moder.sendMessage(config.getString("message.moder.sendMsg").replace("%msg%", message.toString()).replace("%target%", player.getName()));
            player.sendMessage(config.getString("message.moder.reply"));
        }

        return false;
    }
}
