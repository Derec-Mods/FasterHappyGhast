package io.github.derec4.fasterhappyghast.mixin;

import io.github.derec4.fasterhappyghast.Config;
import io.github.derec4.fasterhappyghast.FasterHappyGhast;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin {

    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At("TAIL"))
    private void fasterhappyghast$onSpawn(EntityType<? extends HappyGhast> type, Level level, CallbackInfo ci) {
        Config config = FasterHappyGhast.config();
        if (level.isClientSide() || !config.affectAll) return;

        FasterHappyGhast.setFlyingSpeed((HappyGhast) (Object) this, config.baseSpeed);
    }

    @Inject(method = "addPassenger(Lnet/minecraft/world/entity/Entity;)V", at = @At("TAIL"))
    private void fasterhappyghast$onMount(Entity passenger, CallbackInfo ci) {
        HappyGhast happyGhast = (HappyGhast) (Object) this;
        if (happyGhast.level().isClientSide()) return;

        FasterHappyGhast.setFlyingSpeed(happyGhast, FasterHappyGhast.config().baseSpeed);
    }

    @Inject(method = "removePassenger(Lnet/minecraft/world/entity/Entity;)V", at = @At("TAIL"))
    private void fasterhappyghast$onDismount(Entity passenger, CallbackInfo ci) {
        HappyGhast happyGhast = (HappyGhast) (Object) this;
        if (happyGhast.level().isClientSide() || !happyGhast.getPassengers().isEmpty()) return;

        FasterHappyGhast.setFlyingSpeed(happyGhast, FasterHappyGhast.config().defaultSpeed);
    }
}

