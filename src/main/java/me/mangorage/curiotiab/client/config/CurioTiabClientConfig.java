package me.mangorage.curiotiab.client.config;

import net.minecraftforge.common.ForgeConfigSpec;
import static me.mangorage.curiotiab.common.core.Constants.MODID;

public class CurioTiabClientConfig {
    public static ForgeConfigSpec.ConfigValue<Integer> POSX;
    public static ForgeConfigSpec.ConfigValue<Integer> POSY;
    public static ForgeConfigSpec.ConfigValue<Boolean> USE_HEADER;

    public static void registerClientConfig(ForgeConfigSpec.Builder CLIENT_BUILDER) {
        CLIENT_BUILDER.comment(MODID + "'s Client Config Settings");

        CLIENT_BUILDER.push("guipostion");
        POSX = CLIENT_BUILDER.define("x" , 5);
        POSY = CLIENT_BUILDER.define("y", 5);

        CLIENT_BUILDER.pop();
        CLIENT_BUILDER.comment("Use 'TIAB Curio Header in Overlay'");
        USE_HEADER = CLIENT_BUILDER.define("useheader", true); // default: true
    }
}
