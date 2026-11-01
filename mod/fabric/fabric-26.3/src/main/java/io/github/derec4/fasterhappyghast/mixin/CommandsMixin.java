package io.github.derec4.fasterhappyghast.mixin;

import io.github.derec4.fasterhappyghast.FasterHappyGhast;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Commands.class)
public abstract class CommandsMixin {

    @Inject(method = "<init>(Lnet/minecraft/commands/Commands$CommandSelection;Lnet/minecraft/commands/CommandBuildContext;)V", at = @At("TAIL"))
    private void fasterhappyghast$register(Commands.CommandSelection commandSelection, CommandBuildContext commandBuildContext, CallbackInfo ci) {
        ((Commands) (Object) this).getDispatcher().register(Commands.literal("happyghast")
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.literal("/happyghast reload"), false);
                    return 1;
                })
                .then(Commands.literal("reload")
                        .requires(Commands.hasPermission(Commands.LEVEL_ADMINS))
                        .executes(context -> {
                            FasterHappyGhast.reload();
                            context.getSource().sendSuccess(() -> Component.literal("FasterHappyGhast configuration reloaded."), true);
                            return 1;
                        })));
    }
}
