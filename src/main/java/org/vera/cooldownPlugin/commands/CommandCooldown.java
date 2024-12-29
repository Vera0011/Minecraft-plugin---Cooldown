package org.vera.cooldownPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.vera.cooldownPlugin.internal.Cooldown;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommandCooldown implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length != 3) {
                return false;
            }

            Player playerSelected = Bukkit.getPlayer(args[0]);
            Material material = Material.matchMaterial(args[1]);
            int seconds = Integer.parseInt(args[2]);

            // Validates inputs
            if (playerSelected == null) {
                sender.sendMessage(ChatColor.RED + "[Cooldown] - Player not found");
                return true;
            }

            if (material == null) {
                sender.sendMessage(ChatColor.RED + "[Cooldown] - Material not found");
                return true;
            }

            if (seconds <= 0 || seconds > 31536000) {
                sender.sendMessage(ChatColor.RED + "[Cooldown] - Invalid seconds - Min: 1, Max: 31536000 (365d)");
                return true;
            }

            // Retrieves the future time, always in UTC
            LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
            LocalDateTime futureTime = now.plusSeconds(seconds);
            Date date = Date.from(futureTime.atZone(ZoneId.of("Z")).toInstant());

            boolean result = Cooldown.addUserToCooldown(playerSelected.getUniqueId().toString(), date, material);

            sender.sendMessage(result ? ChatColor.GOLD + "[Cooldown] - User added to cooldown." : ChatColor.RED + "[Cooldown] - No user added to cooldown.");

            return true;
        }

        System.out.println("This command can only be executed by a player");
        return true;
    }

    /**
     * This functions adds the tab completion for users and available items
     *
     * @return list of available items
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();

        // Tab completion for users
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().startsWith(args[0].toLowerCase())) {
                    suggestions.add(player.getName());
                }
            }
        }

        // Adds the tab completion for items
        if (args.length == 2) {
            for (Material item : Material.values()) {
                if (item.getKey().toString().startsWith(args[1].toLowerCase())) {
                    suggestions.add(item.getKey().toString());
                }
            }
        }

        return suggestions;
    }
}
