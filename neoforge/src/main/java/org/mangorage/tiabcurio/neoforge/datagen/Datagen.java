package org.mangorage.tiabcurio.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.mangorage.tiabcurio.common.core.Constants;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Datagen {

    @SubscribeEvent
    public static void onData(GatherDataEvent event) {
        final var helper = event.getExistingFileHelper();
        final var generator = event.getGenerator();
        final var lookupProvider = event.getLookupProvider();
        final var output = event.getGenerator().getPackOutput();

        final var BlockTags = generator.addProvider(event.includeServer(), new BlockTagsProvider(output, lookupProvider, Constants.MOD_ID, helper) {
            @Override
            protected void addTags(HolderLookup.Provider pProvider) {

            }

            @Override
            public String getName() {
                return "whelp";
            }
        });
        generator.addProvider(event.includeServer(), new ItemTagGen(output, lookupProvider, BlockTags.contentsGetter()));
    }
}
