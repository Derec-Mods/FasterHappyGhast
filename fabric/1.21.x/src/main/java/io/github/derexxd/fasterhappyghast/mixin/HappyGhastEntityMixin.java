package io.github.derexxd.fasterhappyghast.mixin;

import io.github.derexxd.fasterhappyghast.Config;
import io.github.derexxd.fasterhappyghast.FasterHappyGhast;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastEntity.class)
public abstract class HappyGhastEntityMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void fasterhappyghast$onSpawn(EntityType<? extends HappyGhastEntity> type, World world, CallbackInfo ci) {
        Config config = FasterHappyGhast.config();
        if (world.isClient() || !config.affectAll) return;

        FasterHappyGhast.setFlyingSpeed((HappyGhastEntity) (Object) this, config.baseSpeed);
    }

    @Inject(method = "addPassenger(Lnet/minecraft/entity/Entity;)V", at = @At("TAIL"))
    private void fasterhappyghast$onMount(Entity passenger, CallbackInfo ci) {
        HappyGhastEntity happyGhast = (HappyGhastEntity) (Object) this;
        if (happyGhast.getWorld().isClient()) return;

        FasterHappyGhast.setFlyingSpeed(happyGhast, FasterHappyGhast.config().baseSpeed);
    }

    @Inject(method = "removePassenger(Lnet/minecraft/entity/Entity;)V", at = @At("TAIL"))
    private void fasterhappyghast$onDismount(Entity passenger, CallbackInfo ci) {
        HappyGhastEntity happyGhast = (HappyGhastEntity) (Object) this;
        if (happyGhast.getWorld().isClient() || !happyGhast.getPassengerList().isEmpty()) return;

        FasterHappyGhast.setFlyingSpeed(happyGhast, FasterHappyGhast.config().defaultSpeed);
    }
}
