package me.mangorage.curiotab.datagen.language;

import me.mangorage.curiotiab.client.ClientRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

import static me.mangorage.curiotiab.common.Constants.MODID;
import static me.mangorage.curiotiab.common.Constants.SLOT_KEY;

public class en_us extends LanguageProvider {
    public en_us(DataGenerator gen) {
        super(gen, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ClientRegistry.useTiab.getName(), "Use time in a bottle");
        add(SLOT_KEY, "Time in a bottle");
    }
}
