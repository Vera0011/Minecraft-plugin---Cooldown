package org.vera.cooldownPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.vera.cooldownPlugin.commands.CommandCooldown;
import org.vera.cooldownPlugin.commands.CommandList;
import org.vera.cooldownPlugin.commands.CommandRemove;
import org.vera.cooldownPlugin.events.ItemUseListener;
import org.vera.cooldownPlugin.events.ItemPickupListener;
import org.vera.cooldownPlugin.placeholders.CooldownPlaceholders;

public final class CooldownPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.loadCommands();
        this.loadEvents();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new CooldownPlaceholders().register();
            getLogger().info("Placeholder API integration is enabled");
        } else {
            getLogger().warning("PlaceholderAPI not found! Placeholder expansions will not work.");
        }

        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }

    private void loadCommands() {
        this.getCommand("cooldown").setExecutor(new CommandCooldown());
        this.getCommand("cooldown").setTabCompleter(new CommandCooldown());

        this.getCommand("cooldown-remove").setExecutor(new CommandRemove());
        this.getCommand("cooldown-remove").setTabCompleter(new CommandRemove());

        this.getCommand("cooldown-list").setExecutor(new CommandList());

        getLogger().info("Commands loaded");
    }

    private void loadEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemUseListener(), this);
        pm.registerEvents(new ItemPickupListener(), this);

        getLogger().info("Events loaded");
    }
}
