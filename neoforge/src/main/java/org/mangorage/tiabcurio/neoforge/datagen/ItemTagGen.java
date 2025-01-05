package org.mangorage.tiabcurio.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.mangorage.tiab.common.api.ICommonTimeInABottleAPI;
import org.mangorage.tiabcurio.common.Constants;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends ItemTagsProvider {


    public ItemTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }


    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        final var curiosTiabTag = tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("curios", "tiab")));
        curiosTiabTag.add(
                ICommonTimeInABottleAPI.COMMON_API.get().getRegistration().getTiabItem().asItem()
        );
    }

    @Override
    public String getName() {
        return Constants.MOD_ID + " Item Tags Provider";
    }
}
