package org.vera.cooldownPlugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.vera.cooldownPlugin.internal.Cooldown;

public class ItemPickupListener implements Listener {

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack item = event.getItem().getItemStack();

            if (Cooldown.hasCooldown(player.getName(), item.getType().toString()).equals("true")) {
                event.setCancelled(true);
            }
        }
    }
}
