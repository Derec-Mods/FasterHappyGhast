package io.github.derexxd.fasterhappyghast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve(FasterHappyGhast.MOD_ID + ".json");

    @SerializedName("base-speed")
    public double baseSpeed = 0.17;

    @SerializedName("default-speed")
    public double defaultSpeed = 0.05;

    @SerializedName("affect-untamed-happy-ghasts")
    public boolean affectAll = false;

    public static Config load() {
        Config config = new Config();
        if (Files.exists(PATH)) {
            try (Reader reader = Files.newBufferedReader(PATH)) {
                Config read = GSON.fromJson(reader, Config.class);
                if (read != null) {
                    config = read;
                }
            } catch (IOException | RuntimeException e) {
                FasterHappyGhast.LOGGER.warn("Failed to read {}, using defaults", PATH, e);
                return config;
            }
        }
        config.save();
        return config;
    }

    private void save() {
        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            FasterHappyGhast.LOGGER.warn("Failed to write {}", PATH, e);
        }
    }
}
