package me.mangorage.curiotiab.client.config;

import me.mangorage.curiotiab.client.screens.overlays.CurioTiabHudOverlay;
import net.minecraftforge.common.ForgeConfigSpec;
import static me.mangorage.curiotiab.common.core.Constants.MODID;

public class CurioTiabClientConfig {
    public enum Type {
        POSX,
        POSY,
        HEADER,
        OVERLAY_MODE
    }

    private static Integer CACHE_POS_X;
    private static Integer CACHE_POS_Y;
    private static Boolean CACHE_USE_HEADER;
    private static CurioTiabHudOverlay.Mode CACHE_OVERLAY_MODE;

    private static ForgeConfigSpec.ConfigValue<Integer> POSX;
    private static ForgeConfigSpec.ConfigValue<Integer> POSY;
    private static ForgeConfigSpec.ConfigValue<Boolean> USE_HEADER;
    private static ForgeConfigSpec.ConfigValue<CurioTiabHudOverlay.Mode> OVERLAY_MODE;

    public static void registerClientConfig(ForgeConfigSpec.Builder CLIENT_BUILDER) {
        CLIENT_BUILDER.comment(MODID + "'s Client Config Settings");

        CLIENT_BUILDER.push("settings");
        CLIENT_BUILDER.comment("Use 'TIAB Curio' Header in Overlay");
        USE_HEADER = CLIENT_BUILDER.define("useheader", true); // default: true
        CLIENT_BUILDER.comment("Define what you want shown on Overlay. Use only One: 'TOTAL', 'CURRENT', OR 'BOTH'");
        CLIENT_BUILDER.comment("Current -> Shows time stored currently on bottle");
        CLIENT_BUILDER.comment("Total -> Shows total Accumulated time on bottle");
        CLIENT_BUILDER.comment("Both -> Shows both Total and Current time");
        OVERLAY_MODE = CLIENT_BUILDER.defineEnum("overlaymode", CurioTiabHudOverlay.Mode.BOTH);
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

    public static CurioTiabHudOverlay.Mode getOverlayMode() {
        if (CACHE_OVERLAY_MODE == null)
            CACHE_OVERLAY_MODE = OVERLAY_MODE.get();
        return CACHE_OVERLAY_MODE;
    }

    public static void setPOSX(int value) {
        CurioTiabClientConfig.POSX.set(value);
    }

    public static void setPOSY(int value) {
        CurioTiabClientConfig.POSY.set(value);
    }

    public static void setOverlayMode(CurioTiabHudOverlay.Mode mode) {
        CurioTiabClientConfig.OVERLAY_MODE.set(mode);
    }

    public static void save(Type... type) {
        for (Type type1 : type) {
            switch (type1) {
                case POSX -> POSX.save();
                case POSY -> POSY.save();
                case HEADER -> USE_HEADER.save();
                case OVERLAY_MODE -> OVERLAY_MODE.save();
            }
        }
    }

    public static void invalidateCache(Type type) { // So we update the Cache's
        switch (type) {
            case POSX -> CACHE_POS_X = null;
            case POSY -> CACHE_POS_Y = null;
            case HEADER -> CACHE_USE_HEADER = null;
            case OVERLAY_MODE -> CACHE_OVERLAY_MODE = null;
        }
    }

    public static void reload() {
        CACHE_POS_X = POSX.get();
        CACHE_POS_Y = POSY.get();
        CACHE_USE_HEADER = USE_HEADER.get();
        CACHE_OVERLAY_MODE = OVERLAY_MODE.get();
    }
}
