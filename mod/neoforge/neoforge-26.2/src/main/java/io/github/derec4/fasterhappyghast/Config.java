package io.github.derec4.fasterhappyghast;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue BASE_SPEED = BUILDER
            .comment("Speed used for the Happy Ghast when mounted")
            .defineInRange("base-speed", 0.17, 0.0, 10.0);

    public static final ModConfigSpec.DoubleValue DEFAULT_SPEED = BUILDER
            .comment("Speed used for the Happy Ghast when not mounted")
            .defineInRange("default-speed", 0.05, 0.0, 10.0);

    public static final ModConfigSpec.BooleanValue AFFECT_UNTAMED_HAPPY_GHASTS = BUILDER
            .comment("If the mod should make untamed Happy Ghasts faster too")
            .define("affect-untamed-happy-ghasts", false);

    public static final ModConfigSpec SPEC = BUILDER.build();
}

