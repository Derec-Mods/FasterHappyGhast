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
//        println("[DEBUG] HappyGhastDismountListener triggered")
        val happyGhast = event.dismounted

        if (happyGhast !is HappyGhast) {
            return
        }

        // Only remove speed boost if no passengers remain after this dismount
        // Bug credits: @DavidS-Repo
        // https://github.com/Derec-Mods/FasterHappyGhast/issues/1
        if (happyGhast.getPassengers.isEmpty()) {
            happyGhast.getAttribute(Attribute.FLYING_SPEED)?.baseValue = FasterHappyGhast.defaultSpeed
        }
    }
}