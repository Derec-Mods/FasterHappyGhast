package io.github.derec4.fasterHappyGhast.listeners

import io.papermc.paper.registry.keys.tags.EntityTypeTagKeys
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent

class HappyGhastSpawnListener : Listener {
    @EventHandler
    fun onHappyGhastSpawn(event: CreatureSpawnEvent) {
        val happyGhast = event.entity;

        if(happyGhast)
    }
}