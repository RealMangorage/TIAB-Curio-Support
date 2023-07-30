package me.mangorage.curiotab.datagen.providers.language;

import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModdedLanguageProvider extends LanguageProvider {

    public ModdedLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
    }

    public void add(Translatable translatable, String value) {
        add(translatable.getKey(), value);
    }
}
