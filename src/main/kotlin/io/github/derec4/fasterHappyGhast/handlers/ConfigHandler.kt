package io.github.derec4.fasterHappyGhast.handlers

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class ConfigHandler {
    var baseSpeed: Double = 0.17
    var defaultSpeed: Double = 0.05
    var affectAll: Boolean = false

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
        config.setComments(
            "base-speed", listOf(
                "By default only riding a happy ghast will boost its speed; dismounting will return it to normal",
                "Speed used for the Happy Ghast (attribute:flying_speed base in vanilla mob stats)"
            )
        )
        config.addDefault("base-speed", baseSpeed)
        config.setComments(
            "default-speed", listOf(
                "Speed used for the Happy Ghast when not mounted (attribute:flying_speed base in vanilla mob stats)"
            )
        )
        config.addDefault("default-speed", defaultSpeed)
//        config.setComments(
//            "always-affect-tamed-ghasts", listOf(
//                "If the increased speed should happen always (not only when a player is riding the Happy Ghast)"
//            )
//        )
//        config.addDefault("always-affect-tamed-ghasts", affectHarnessOnly)
        config.setComments(
            "affect-untamed-happy-ghasts", listOf(
                "If the plugin should make untamed Happy Ghasts faster too"
            )
        )
        config.addDefault("affect-untamed-happy-ghasts", affectAll)
        config.options().copyDefaults(true)
    }

    fun load(plugin: JavaPlugin) {
        val config = plugin.config
        baseSpeed = config.getDouble("base-speed", 0.17)
        defaultSpeed = config.getDouble("default-speed", 0.05)
        affectAll = config.getBoolean("affect-untamed-happy-ghasts", false)
    }

    fun save(plugin: JavaPlugin) {
        val config = plugin.config
        config.set("base-speed", baseSpeed)
        config.set("default-speed", defaultSpeed)
        config.set("affect-untamed-happy-ghasts", affectAll)
        plugin.saveConfig()
    }
}