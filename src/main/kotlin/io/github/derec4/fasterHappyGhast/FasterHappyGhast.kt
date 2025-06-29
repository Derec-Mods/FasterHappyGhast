package io.github.derec4.fasterHappyGhast

import io.github.derec4.fasterHappyGhast.listeners.HappyGhastSpawnListener
import io.github.derec4.fasterHappyGhast.listeners.HappyGhastTameListener
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class FasterHappyGhast : JavaPlugin() {
    companion object Config {
        var baseSpeed: Double = 0.17
        var affectAll: Boolean = false;
    }

    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        baseSpeed = config.getDouble("base-speed", 0.17);
        affectAll = config.getBoolean("affect-untamed-happy-ghasts", false);

        server.pluginManager.registerEvents(HappyGhastSpawnListener(), this);
        server.pluginManager.registerEvents(HappyGhastTameListener(), this);

        this.componentLogger.info("")
        this.componentLogger.info("${NamedTextColor.GREEN}  |_______|                             ")
        this.componentLogger.info("${NamedTextColor.GREEN}  | Derex |     Derex Faster Happy Ghasts v1.0.0.0")
        this.componentLogger.info("${NamedTextColor.GREEN}  |_______|     Running on ${Bukkit.getName()} - ${Bukkit.getVersion()}")
        this.componentLogger.info("")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
