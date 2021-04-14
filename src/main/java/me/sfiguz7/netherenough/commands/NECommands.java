package me.sfiguz7.netherenough.commands;

import me.mrCookieSlime.Slimefun.cscorelib2.chat.ChatColors;
import me.sfiguz7.netherenough.NetherEnough;
import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class NECommands implements CommandExecutor {

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 2
            && args[0].equalsIgnoreCase("tpworld")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("ne.command.tpworld")) {
                    World world = Bukkit.getWorld(args[1]);
                    Utils.teleportToWorld(p, world);
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "NetherEnough > " + ChatColor.RED +
                        "Insufficient permissions!");
                }
            }
        } else {
            sendHelp(sender);
        }
        return true;
    }

    public void sendHelp(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage(ChatColors.color("&aNetherEnough &2v" + NetherEnough.getVersion()));

        if (sender.hasPermission("ne.command.tpworld")) {
            sender.sendMessage(ChatColors.color("&3/ne tpworld <world> &b") + "Teleports you to <world>");
        }
    }

}


