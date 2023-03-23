package me.mangorage.curiotab.datagen.providers.language;

import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.checkerframework.checker.units.qual.A;

import static me.mangorage.curiotiab.common.core.Constants.MODID;
import static me.mangorage.curiotiab.common.core.Translatable.*;

public class en_us extends LanguageProvider {
    public en_us(DataGenerator gen) {
        super(gen, MODID, "en_us");
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
        add(SCREEN_RESET, "Right Click to reset overlay settings");
        add(SCREEN_SAVE, "Double Left Click to save overlay settings");
    }

    private void add(Translatable translatable, String value) {
        add(translatable.getKey(), value);
    }

}
