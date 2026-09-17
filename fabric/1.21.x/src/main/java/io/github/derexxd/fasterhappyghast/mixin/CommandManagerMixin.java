package io.github.derexxd.fasterhappyghast.mixin;

import io.github.derexxd.fasterhappyghast.FasterHappyGhast;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public abstract class CommandManagerMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void fasterhappyghast$register(CommandManager.RegistrationEnvironment environment, CommandRegistryAccess registryAccess, CallbackInfo ci) {
        ((CommandManager) (Object) this).getDispatcher().register(CommandManager.literal("happyghast")
                .executes(context -> {
                    context.getSource().sendFeedback(() -> Text.literal("/happyghast reload"), false);
                    return 1;
                })
                .then(CommandManager.literal("reload")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(context -> {
                            FasterHappyGhast.reload();
                            context.getSource().sendFeedback(() -> Text.literal("FasterHappyGhast configuration reloaded."), true);
                            return 1;
                        })));
    }
}
