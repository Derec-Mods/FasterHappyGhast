package io.github.derec4.fasterHappyGhast.listeners

import io.github.derec4.fasterHappyGhast.FasterHappyGhast
import io.papermc.paper.event.entity.EntityEquipmentChangedEvent
import org.bukkit.attribute.Attribute
import org.bukkit.entity.HappyGhast
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class HappyGhastEquipListener: Listener {
    @EventHandler
    fun onHappyGhastEquip (event: EntityEquipmentChangedEvent) {
//        println("[DEBUG] HappyGhastEquipListener triggered")
        val happyGhast = event.entity;

        if (happyGhast !is HappyGhast) {
            return;
        }

        if (FasterHappyGhast.affectHarnessOnly) {
            happyGhast.getAttribute(Attribute.FLYING_SPEED)?.baseValue = FasterHappyGhast.baseSpeed;
        }
    }
}