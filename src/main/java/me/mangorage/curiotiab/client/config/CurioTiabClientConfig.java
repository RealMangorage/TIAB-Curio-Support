package me.mangorage.curiotiab.client.config;

import net.minecraftforge.common.ForgeConfigSpec;
import static me.mangorage.curiotiab.common.core.Constants.MODID;

public class CurioTiabClientConfig {
    public enum Type {
        POSX,
        POSY,
        HEADER;
    }

    protected static Integer CACHE_POS_X;
    protected static Integer CACHE_POS_Y;
    protected static Boolean CACHE_USE_HEADER;

    public static ForgeConfigSpec.ConfigValue<Integer> POSX;
    public static ForgeConfigSpec.ConfigValue<Integer> POSY;
    public static ForgeConfigSpec.ConfigValue<Boolean> USE_HEADER;

    public static void registerClientConfig(ForgeConfigSpec.Builder CLIENT_BUILDER) {
        CLIENT_BUILDER.comment(MODID + "'s Client Config Settings");

        CLIENT_BUILDER.push("settings");
        CLIENT_BUILDER.comment("Use 'TIAB Curio' Header in Overlay");
        USE_HEADER = CLIENT_BUILDER.define("useheader", true); // default: true

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("guipostion");
        POSX = CLIENT_BUILDER.define("x" , 5);
        POSY = CLIENT_BUILDER.define("y", 5);
    }

    public static int getPosX() {
        if (CACHE_POS_X == null)
            CACHE_POS_X = POSX.get();
        return CACHE_POS_X;
    }

    public static int getPosY() {
        if (CACHE_POS_Y == null)
            CACHE_POS_Y = POSY.get();
        return CACHE_POS_Y;
    }

    public static boolean useHeader() {
        if (CACHE_USE_HEADER == null)
            CACHE_USE_HEADER = USE_HEADER.get();
        return CACHE_USE_HEADER;
    }

    public static void invalidateCache(Type type) { // So we update the Cache's
        switch (type) {
            case POSX -> CACHE_POS_X = null;
            case POSY -> CACHE_POS_Y = null;
            case HEADER -> CACHE_USE_HEADER = null;
        }
    }

    public static void reload() {
        CACHE_POS_X = POSX.get();
        CACHE_POS_Y = POSY.get();
        CACHE_USE_HEADER = USE_HEADER.get();
    }
}
