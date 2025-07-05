package io.github.derec4.fasterHappyGhast.handlers

import io.github.derec4.fasterHappyGhast.FasterHappyGhast
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class ConfigHandler {
    var baseSpeed: Double = 0.17;
    var affectAll: Boolean = false;

    fun load(plugin: JavaPlugin) {
        val config = plugin.config;
        FasterHappyGhast.Config.baseSpeed = config.getDouble("base-speed", 0.17);
        FasterHappyGhast.Config.affectAll = config.getBoolean("affect-untamed-happy-ghasts", false);
    }

    fun save(plugin: JavaPlugin) {
        val config = plugin.config
        config.set("base-speed", FasterHappyGhast.Config.baseSpeed)
        config.set("affect-untamed-happy-ghasts", FasterHappyGhast.Config.affectAll)
        plugin.saveConfig()
    }

    fun generateDefaultConfig(config: FileConfiguration) {
        config.options().setHeader(
            listOf(
                "--------------------------------------------------",
                "Happy Ghast vanilla friendly plugin by DerexXD",
                "Report all ur troubles at the link below!",
                "coming soon",
                "--------------------------------------------------"
            )
        )
        config.addDefault("base-speed", 0.17)
        config.addDefault("affect-untamed-happy-ghasts", false)
        config.options().copyDefaults(true)
    }
}