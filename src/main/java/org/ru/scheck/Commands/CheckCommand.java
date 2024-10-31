package org.ru.scheck.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.ru.scheck.SCheck;
import org.ru.scheck.Utils.Cheater.CheatPlayer;
import org.ru.scheck.Utils.Color;
import org.ru.scheck.Utils.ConfigUtils;

public class CheckCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            System.out.println(Color.translate("&cТолько игрок может использовать команду!"));
            return true;
        }

        Player player = (Player) sender;
        ConfigUtils config = new ConfigUtils(SCheck.getInstance);

        if (player.hasPermission("scheck.admin")) {
            if (args.length > 0) {
                if (args[0].equals("reload") && Bukkit.getPlayer(args[0]) == null) {
                    SCheck.getInstance.reloadConfig();
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);

                if (target == player) return true;

                CheatPlayer cheat = new CheatPlayer(target, player);

                if (!CheatPlayer.isCheck(target)) {
                    cheat.check();
                    player.sendMessage(config.getString("message.sender").replace("%target%", target.getName()));
                    return true;
                }
                player.sendMessage(cheat.removeCheck());
                return true;

            }
            config.sendListMessages(config.getList("help"), player);
            return true;
        }
        player.sendMessage(config.getString("message.noPerm"));

        return true;
    }
}
