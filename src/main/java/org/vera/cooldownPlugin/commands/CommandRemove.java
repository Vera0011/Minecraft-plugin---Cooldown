package org.vera.cooldownPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.vera.cooldownPlugin.internal.Cooldown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommandRemove implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "[Cooldown] - Player not found");
            return true;
        }

        boolean result = Cooldown.removeUserFromCooldown(target.getUniqueId().toString());

        sender.sendMessage(result ? ChatColor.GOLD + "[Cooldown] - User removed from cooldown." : ChatColor.RED + "[Cooldown] - No user removed from cooldown.");

        return true;
    }

    /**
     * This functions adds the tab completion for users with cooldown
     *
     * @return list of available items
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();
        List<String> usersWithCooldown = new ArrayList<>();
        HashMap<String, ArrayList<HashMap<String, String>>> users = Cooldown.getMarkedPlayers();

        for (String userId : users.keySet()) {
            Player currentUser = Bukkit.getPlayer(UUID.fromString(userId));

            // If user is not found, remove it from the list
            if (currentUser != null) {
                usersWithCooldown.add(currentUser.getName());
            } else {
                Cooldown.removeUserFromCooldown(userId);
            }
        }

        // Tab completion for users
        if (args.length == 1) {
            for (String userName : usersWithCooldown) {
                if (userName.startsWith(args[0].toLowerCase())) {
                    suggestions.add(userName);
                }
            }
        }

        if (suggestions.isEmpty()) {
            suggestions.add("No users found");
        }

        return suggestions;
    }
}
