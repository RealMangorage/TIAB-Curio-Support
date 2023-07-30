package me.mangorage.curiotab.datagen;


import me.mangorage.curiotab.datagen.providers.BlockTagsGen;
import me.mangorage.curiotab.datagen.providers.ItemTagGen;
import me.mangorage.curiotab.datagen.providers.language.en_us;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.mangorage.curiotiab.common.core.Constants.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final var helper = event.getExistingFileHelper();
        final var generator = event.getGenerator();
        final var lookupProvider = event.getLookupProvider();
        final var output = event.getGenerator().getPackOutput();

        final var BlockTags = generator.addProvider(event.includeServer(), new BlockTagsGen(output, lookupProvider, helper));
        generator.addProvider(event.includeServer(), new ItemTagGen(output, lookupProvider, BlockTags.contentsGetter()));
        generator.addProvider(event.includeClient(), new en_us(output));
    }
}