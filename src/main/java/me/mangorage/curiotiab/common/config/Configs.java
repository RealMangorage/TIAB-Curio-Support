package me.mangorage.curiotiab.common.config;

import me.mangorage.curiotiab.client.config.CurioTiabClientConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Configs {
    public static ForgeConfigSpec.Builder SERVER_BUILDER;
    public static ForgeConfigSpec.Builder COMMON_BUILDER;

    public static ForgeConfigSpec.Builder CLIENT_BUILDER;
    public static void register() {
        registerServerConfigs();
        registerCommonConfigs();
        registerClientConfigs();
    }

    private static void registerServerConfigs() {
        SERVER_BUILDER = new ForgeConfigSpec.Builder();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

    private static void registerCommonConfigs() {
        COMMON_BUILDER = new ForgeConfigSpec.Builder();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }

    private static void registerClientConfigs() {
        CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CurioTiabClientConfig.registerClientConfig(CLIENT_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }
}
