package io.github.derec4.fasterHappyGhast.listeners

import io.github.derec4.fasterHappyGhast.FasterHappyGhast
import org.bukkit.attribute.Attribute
import org.bukkit.entity.HappyGhast
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDismountEvent

class HappyGhastDismountListener: Listener {
    @EventHandler
    fun onHappyGhastMount(event: EntityDismountEvent) {
        println("[DEBUG] HappyGhastDismountListener triggered")
        val happyGhast = event.dismounted

        if (happyGhast !is HappyGhast) {
            return
        }

        happyGhast.getAttribute(Attribute.FLYING_SPEED)?.baseValue = FasterHappyGhast.defaultSpeed
    }
}