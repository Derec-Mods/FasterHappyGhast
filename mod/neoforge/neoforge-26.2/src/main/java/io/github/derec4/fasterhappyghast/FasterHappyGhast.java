package io.github.derec4.fasterhappyghast;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityMountEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Mod(FasterHappyGhast.MODID)
public class FasterHappyGhast {
    public static final String MODID = "fasterhappyghast";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public FasterHappyGhast(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.register(this);
    }

    public static void setFlyingSpeed(LivingEntity entity, double value) {
        AttributeInstance attribute = entity.getAttribute(Attributes.FLYING_SPEED);
        if (attribute != null) {
            attribute.setBaseValue(value);
        }
    }

    @SubscribeEvent
    public void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide() || !Config.AFFECT_UNTAMED_HAPPY_GHASTS.get()) {
            return;
        }

        if (event.getEntity() instanceof HappyGhast happyGhast) {
            setFlyingSpeed(happyGhast, Config.BASE_SPEED.get());
        }
    }

    @SubscribeEvent
    public void onEntityMount(EntityMountEvent event) {
        if (event.getLevel().isClientSide()) {
            return;
        }

        if (event.getEntityBeingMounted() instanceof HappyGhast happyGhast) {
            if (event.isMounting()) {
                setFlyingSpeed(happyGhast, Config.BASE_SPEED.get());
            } else if (event.isDismounting()) {
                List<Entity> remaining = happyGhast.getPassengers().stream()
                        .filter(e -> e != event.getEntityMounting())
                        .toList();
                if (remaining.isEmpty()) {
                    setFlyingSpeed(happyGhast, Config.DEFAULT_SPEED.get());
                }
            }
        }
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("happyghast")
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.literal("/happyghast reload"), false);
                    return 1;
                })
                .then(Commands.literal("reload")
                        .requires(Commands.hasPermission(Commands.LEVEL_ADMINS))
                        .executes(context -> {
                            Config.SPEC.afterReload();
                            context.getSource().sendSuccess(() -> Component.literal("FasterHappyGhast configuration reloaded."), true);
                            return 1;
                        })));
    }
}
