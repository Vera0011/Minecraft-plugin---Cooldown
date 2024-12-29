package org.vera.cooldownPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.vera.cooldownPlugin.commands.CommandCooldown;
import org.vera.cooldownPlugin.commands.CommandList;
import org.vera.cooldownPlugin.commands.CommandRemove;

public final class CooldownPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin enabled - Created by Vera0011");
        this.getCommand("cooldown").setExecutor(new CommandCooldown());
        this.getCommand("cooldown").setTabCompleter(new CommandCooldown());

        this.getCommand("cooldown-remove").setExecutor(new CommandRemove());
        this.getCommand("cooldown-remove").setTabCompleter(new CommandRemove());

        //this.getCommand("cooldown-list").setExecutor(new CommandList());
        //this.getCommand("cooldown-list").setTabCompleter(new CommandList());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled - Created by Vera0011");
    }
}
