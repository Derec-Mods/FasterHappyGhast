package io.github.derec4.fasterHappyGhast.handlers

import io.github.derec4.fasterHappyGhast.FasterHappyGhast
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class ConfigHandler {
    var baseSpeed: Double = 0.17
    var defaultSpeed: Double = 0.03
    var affectAll: Boolean = false
    var affectHarnessOnly: Boolean = true

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
        config.addDefault("base-speed", baseSpeed)
        config.addDefault("default-speed", defaultSpeed)
        config.addDefault("affect-untamed-happy-ghasts", affectAll)
        config.addDefault("always-affect-tamed-ghasts", affectHarnessOnly)
        config.options().copyDefaults(true)
    }

    fun load(plugin: JavaPlugin) {
        val config = plugin.config
        baseSpeed = config.getDouble("base-speed", 0.17)
        defaultSpeed = config.getDouble("default-speed", 0.05)
        affectAll = config.getBoolean("affect-untamed-happy-ghasts", false)
        affectHarnessOnly = config.getBoolean("always-affect-tamed-ghasts", true)
    }

    fun save(plugin: JavaPlugin) {
        val config = plugin.config
        config.set("base-speed", baseSpeed)
        config.set("default-speed", defaultSpeed)
        config.set("affect-untamed-happy-ghasts", affectAll)
        config.set("always-affect-tamed-ghasts", affectHarnessOnly)
        plugin.saveConfig()
    }
}