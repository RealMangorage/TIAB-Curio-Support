package me.mangorage.curiotab.datagen;


import me.mangorage.curiotab.datagen.language.en_us;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.mangorage.curiotiab.common.Constants.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final var helper = event.getExistingFileHelper();
        final var generator = event.getGenerator();

        var BlockTagsProvider = new BlockTagsProvider(generator, MODID, helper);

        generator.addProvider(event.includeServer(), BlockTagsProvider);
        generator.addProvider(event.includeServer(), new ItemTagGen(generator, BlockTagsProvider, helper));

        generator.addProvider(event.includeClient(), new en_us(generator));
    }
}