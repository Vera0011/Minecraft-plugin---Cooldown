package org.vera.cooldownPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.cooldownPlugin.internal.Cooldown;

public class CommandRemove implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: /remove-cooldown <player>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            System.out.println("Player not found");
            return false;
        }

        boolean result = Cooldown.removeUserFromCooldown(target.getUniqueId().toString());

        sender.sendMessage(result ? ChatColor.GOLD + "[Cooldown] - User removed from cooldown." : ChatColor.RED + "[Cooldown] - No user removed from cooldown.");

        return true;
    }
}
