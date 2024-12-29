package org.vera.cooldownPlugin.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.vera.cooldownPlugin.internal.Cooldown;

/**
 * This class represents the interaction with the PlaceholderAPI
 */
public class CooldownPlaceholders extends PlaceholderExpansion {
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {
        if (identifier.startsWith("cooldown_")) {
            String[] args = identifier.split("_");

            if (args.length == 3) {
                String targetPlayerName = args[1];
                String itemName = args[2];

                return Cooldown.hasCooldown(targetPlayerName, itemName);
            }
        }

        return null;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "cooldownPlugin";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Vera0011";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
}
