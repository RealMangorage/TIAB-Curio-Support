package me.mangorage.curiotab.datagen.providers;

import me.mangorage.curiotiab.common.core.Constants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

import static me.mangorage.curiotiab.common.core.Constants.MODID;


public class ItemTagGen extends ItemTagsProvider {


    public ItemTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }


    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        final var curiosTiabTag = tag(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("curios", "tiab")));
        curiosTiabTag.add(Constants.TIAB_ITEM);
    }

    @Override
    public String getName() {
        return MODID + " Item Tags Provider";
    }
}
