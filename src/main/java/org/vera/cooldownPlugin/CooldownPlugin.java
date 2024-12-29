package org.vera.cooldownPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.vera.cooldownPlugin.commands.CommandCooldown;
import org.vera.cooldownPlugin.commands.CommandList;
import org.vera.cooldownPlugin.commands.CommandRemove;
import org.vera.cooldownPlugin.placeholders.CooldownPlaceholders;

public final class CooldownPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.loadCommands();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new CooldownPlaceholders().register();
            getLogger().info("Placeholder API integration is enabled");
        } else {
            getLogger().warning("PlaceholderAPI not found! Placeholder expansions will not work.");
        }

        getLogger().info("Plugin enabled - Created by Vera0011");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled - Created by Vera0011");
    }

    private void loadCommands() {
        this.getCommand("cooldown").setExecutor(new CommandCooldown());
        this.getCommand("cooldown").setTabCompleter(new CommandCooldown());

        this.getCommand("cooldown-remove").setExecutor(new CommandRemove());
        this.getCommand("cooldown-remove").setTabCompleter(new CommandRemove());

        this.getCommand("cooldown-list").setExecutor(new CommandList());
    }
}
