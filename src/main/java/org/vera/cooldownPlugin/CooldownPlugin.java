package org.vera.cooldownPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.vera.cooldownPlugin.commands.CommandCooldown;

public final class CooldownPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin enabled - Created by Vera0011");
        this.getCommand("cooldown").setExecutor(new CommandCooldown());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled - Created by Vera0011");
    }
}
