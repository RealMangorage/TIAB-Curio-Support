package me.mangorage.curiotab.datagen.providers.language;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import static me.mangorage.curiotiab.common.core.Constants.MODID;
import static me.mangorage.curiotiab.common.core.Translatable.*;

public class en_us extends ModdedLanguageProvider {
    public en_us(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(USE_KEY, "Use TIAB");
        add(TOGGLE_KEY, "Toggle TIAB overlay");
        add(SLOT_KEY, "TIAB");

        add(CATEGORY_KEY, "Time in a bottle Curio support");

        add(MSG_RESET, "Curio TIAB overlay position reset");
        add(MSG_SAVED, "Curio TIAB overlay position saved");

        add(SCREEN_DRAG, "Click and drag to move this around");
        add(SCREEN_CONFIG, "Curio TIAB config screen");
        add(SCREEN_CLOSE, "Press ESC to exit config screen");
        add(SCREEN_CLOSED, "Closed Curio TIAB config Screen");
        add(SCREEN_RESET, "Shift + R to reset overlay settings");
        add(SCREEN_SAVE, "Shift + S to save overlay settings");
        add(COMMAND_RELOAD_CLIENT, "Reload Client Configs");
    }
}
