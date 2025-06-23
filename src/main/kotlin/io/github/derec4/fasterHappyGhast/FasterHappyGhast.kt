package io.github.derec4.fasterHappyGhast

import io.github.derec4.fasterHappyGhast.listeners.HappyGhastSpawnListener
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class FasterHappyGhast : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(HappyGhastSpawnListener(), this)

        Bukkit.getLogger().info("")
        Bukkit.getServer().consoleSender.sendMessage("${NamedTextColor.GREEN}  |_______|                             ")
        Bukkit.getServer().consoleSender.sendMessage("${NamedTextColor.GREEN}  | Derex |     Derex Despawn v1.0.0")
        Bukkit.getServer().consoleSender.sendMessage("${NamedTextColor.GREEN}  |_______|     Running on ${Bukkit.getName()} - ${Bukkit.getVersion()}")
        Bukkit.getLogger().info("")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
