package me.mangorage.curiotab.datagen;


import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.mangorage.curiotiab.common.Constants.MODID;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final var helper = event.getExistingFileHelper();
        final var generator = event.getGenerator();

        var BlockTagsProvider = new BlockTagsProvider(generator, Constants.MODID, helper);

        generator.addProvider(event.includeServer(), BlockTagsProvider);
        generator.addProvider(event.includeServer(), new ItemTagGen(generator, BlockTagsProvider, Constants.MODID, helper));
    }
}