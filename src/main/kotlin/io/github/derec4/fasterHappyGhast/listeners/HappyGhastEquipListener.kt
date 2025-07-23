package io.github.derec4.fasterHappyGhast.listeners

import io.github.derec4.fasterHappyGhast.FasterHappyGhast
import io.papermc.paper.event.entity.EntityEquipmentChangedEvent
import org.bukkit.attribute.Attribute
import org.bukkit.entity.HappyGhast
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityTameEvent

class HappyGhastEquipListener: Listener {
    @EventHandler
    fun onHappyGhastEquip (event: EntityEquipmentChangedEvent) {
        val happyGhast = event.entity;

        if (happyGhast !is HappyGhast) {
            return;
        }

        if (FasterHappyGhast.alwaysAffect) {
            happyGhast.getAttribute(Attribute.FLYING_SPEED)?.baseValue = FasterHappyGhast.baseSpeed;
        }
    }
}