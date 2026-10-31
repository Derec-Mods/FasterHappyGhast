package io.github.derec4.fasterHappyGhast

import io.github.derec4.fasterHappyGhast.commands.CommandManager
import io.github.derec4.fasterHappyGhast.handlers.ConfigHandler
import io.github.derec4.fasterHappyGhast.listeners.HappyGhastDismountListener
import io.github.derec4.fasterHappyGhast.listeners.HappyGhastMountListener
import io.github.derec4.fasterHappyGhast.listeners.HappyGhastSpawnListener
import io.github.derec4.fasterHappyGhast.listeners.HappyGhastTameListener
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class FasterHappyGhast : JavaPlugin() {
    companion object Config {
        var baseSpeed: Double = 0.0
        var defaultSpeed: Double = 0.0
        var affectAll: Boolean = false
        var affectHarnessOnly: Boolean = false
    }

    override fun onEnable() {
        // Plugin startup logic
        val configHandler = ConfigHandler()
        configHandler.generateDefaultConfig(config)
        config.options().copyDefaults(true)
        saveConfig()
        configHandler.load(this)

        baseSpeed = configHandler.baseSpeed
        defaultSpeed = configHandler.defaultSpeed
        affectAll = configHandler.affectAll

        server.pluginManager.registerEvents(HappyGhastSpawnListener(), this)
        server.pluginManager.registerEvents(HappyGhastTameListener(), this)
        server.pluginManager.registerEvents(HappyGhastMountListener(), this)
        server.pluginManager.registerEvents(HappyGhastDismountListener(), this)

        this.getCommand("happyghast")?.setExecutor(CommandManager(this))

        this.componentLogger.info(Component.empty())
        this.componentLogger.info(Component.text("  |_______|                             ", NamedTextColor.GREEN))
        this.componentLogger.info(
            Component.text(
                "  | Derex |     Derex Faster Happy Ghasts v${description.version}",
                NamedTextColor.GREEN
            )
        )
        this.componentLogger.info(
            Component.text(
                "  |_______|     Running on ${Bukkit.getName()} - ${Bukkit.getVersion()}",
                NamedTextColor.GREEN
            )
        )
        this.componentLogger.info(Component.empty())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
