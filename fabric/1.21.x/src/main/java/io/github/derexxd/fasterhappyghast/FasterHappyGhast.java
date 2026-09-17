package io.github.derexxd.fasterhappyghast;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FasterHappyGhast implements ModInitializer {
    public static final String MOD_ID = "fasterhappyghast";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static Config config = new Config();

    public static Config config() {
        return config;
    }

    public static void reload() {
        config = Config.load();
    }

    public static void setFlyingSpeed(LivingEntity entity, double value) {
        EntityAttributeInstance attribute = entity.getAttributeInstance(EntityAttributes.FLYING_SPEED);
        if (attribute != null) {
            attribute.setBaseValue(value);
        }
    }

    @Override
    public void onInitialize() {
        reload();
    }
}
