package io.github.derec4.fasterHappyGhast

import io.github.derec4.fasterHappyGhast.listeners.HappyGhastSpawnListener
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class FasterHappyGhast : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(HappyGhastSpawnListener(), this)

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
