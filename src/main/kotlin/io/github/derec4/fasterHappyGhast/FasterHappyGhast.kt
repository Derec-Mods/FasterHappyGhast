package io.github.derec4.fasterHappyGhast

import io.github.derec4.fasterHappyGhast.handlers.ConfigHandler
import io.github.derec4.fasterHappyGhast.listeners.*
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class FasterHappyGhast : JavaPlugin() {
    companion object Config {
        var baseSpeed: Double = 0.17
        var defaultSpeed: Double = 0.03
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

        Config.baseSpeed = configHandler.baseSpeed
        Config.defaultSpeed = configHandler.defaultSpeed
        Config.affectAll = configHandler.affectAll
        Config.affectHarnessOnly = configHandler.affectHarnessOnly

        server.pluginManager.registerEvents(HappyGhastSpawnListener(), this)
        server.pluginManager.registerEvents(HappyGhastTameListener(), this)
        server.pluginManager.registerEvents(HappyGhastEquipListener(), this)
        server.pluginManager.registerEvents(HappyGhastMountListener(), this)
        server.pluginManager.registerEvents(HappyGhastDismountListener(), this)

        this.componentLogger.info("")
        this.componentLogger.info("${NamedTextColor.GREEN}  |_______|                             ")
        this.componentLogger.info("${NamedTextColor.GREEN}  | Derex |     Derex Faster Happy Ghasts v" + description.version)
        this.componentLogger.info("${NamedTextColor.GREEN}  |_______|     Running on ${Bukkit.getName()} - ${Bukkit.getVersion()}")
        this.componentLogger.info("")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
