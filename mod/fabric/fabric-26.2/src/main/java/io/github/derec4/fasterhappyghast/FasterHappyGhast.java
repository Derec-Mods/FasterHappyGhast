package io.github.derec4.fasterhappyghast;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
        AttributeInstance attribute = entity.getAttribute(Attributes.FLYING_SPEED);
        if (attribute != null) {
            attribute.setBaseValue(value);
        }
    }

    @Override
    public void onInitialize() {
        reload();
    }
}
