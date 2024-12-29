package org.vera.cooldownPlugin.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.vera.cooldownPlugin.internal.Cooldown;

public class ItemUseListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) && item != null) {
            Material itemType = item.getType();

            if (Cooldown.hasCooldown(player.getName(), itemType.toString()).equals("true")) {
                player.sendMessage(ChatColor.RED + "[Cooldown] - You are not allowed to place that block");
                event.setCancelled(true);
            }
        }
    }
}