package me.mangorage.curiotab.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemTagGen extends ItemTagsProvider {
    public ItemTagGen(DataGenerator generator, boolean includeServer, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, MODID, existingFileHelper);
        generator.addProvider(includeServer, blockTagsProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        final var curiosCharmTag = tag(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("curios", "tiab")));
        curiosCharmTag.add(Constants.TIAB_ITEM);
    }

    @Override
    public String getName() {
        return MODID + " Item Tags Provider";
    }
}
