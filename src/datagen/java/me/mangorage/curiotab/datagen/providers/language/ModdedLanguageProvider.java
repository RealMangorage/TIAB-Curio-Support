package me.mangorage.curiotab.datagen.providers.language;

import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModdedLanguageProvider extends LanguageProvider {
    public ModdedLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {

    }

    public void add(Translatable translatable, String value) {
        add(translatable.getKey(), value);
    }
}
