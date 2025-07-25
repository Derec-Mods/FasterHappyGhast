package io.github.derec4.fasterHappyGhast.listeners

import io.github.derec4.fasterHappyGhast.FasterHappyGhast
import org.bukkit.attribute.Attribute
import org.bukkit.entity.HappyGhast
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityMountEvent

class HappyGhastMountListener: Listener {
    @EventHandler
    fun onHappyGhastMount (event: EntityMountEvent) {
//        println("[DEBUG] HappyGhastMountListener triggered")
        val happyGhast = event.mount;

        if (happyGhast !is HappyGhast) {
            return;
        }

        happyGhast.getAttribute(Attribute.FLYING_SPEED)?.baseValue = FasterHappyGhast.baseSpeed;
    }
}