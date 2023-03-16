package me.mangorage.curiotab.datagen.providers.language;

import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static me.mangorage.curiotiab.common.core.Constants.MODID;
import static me.mangorage.curiotiab.common.core.Translatable.*;

public class en_us extends LanguageProvider {
    public en_us(DataGenerator gen) {
        super(gen, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(USE_KEY.getKey(), "Use time in a bottle");
        add(TOGGLE_KEY.getKey(), "Toggle gui overlay element");
        add(SLOT_KEY.getKey(), "TIAB");

        add(CATEGORY_KEY.getKey(), "Time In A Bottle Curio Support");

        add(MSG_DISABLED.getKey(), "Modify CurioTiab GUI Disabled");
        add(MSG_ENABLED.getKey(), "Modify CurioTiab GUI Enabled");
        add(MSG_RESET.getKey(), "Modify CurioTiab GUI Position Reset");
        add(MSG_SAVED.getKey(), "Modify CurioTiab GUI Position Saved");
    }

}
