package org.vera.cooldownPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.cooldownPlugin.internal.Cooldown;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class CommandList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            StringBuilder sb = new StringBuilder();

            for (String user : Cooldown.getMarkedPlayers().keySet()) {
                ArrayList<HashMap<String, String>> dataFromUser = Cooldown.getMarkedPlayers().get(user);
                Player playerUser = Bukkit.getPlayer(UUID.fromString(user));
                StringBuilder sbMaterials = new StringBuilder();

                for (HashMap<String, String> materials : dataFromUser) {
                    sbMaterials.append(materials.get("material")).append(", ");
                }

                if (playerUser != null) {
                    sb.append(playerUser.getName()).append(" --> ").append(sbMaterials).append("\n");
                }
            }
            
            if (sb.isEmpty()) {
                sender.sendMessage(ChatColor.GOLD + "[Cooldown] - No users found");
                return true;
            }

            sender.sendMessage(ChatColor.GOLD + "[Cooldown] - Users and data found: \n" + ChatColor.AQUA + sb);

            return true;
        }

        System.out.println("This command can only be executed by a player");
        return true;
    }
}
