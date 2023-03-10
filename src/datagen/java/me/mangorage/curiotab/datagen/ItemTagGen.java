package me.mangorage.curiotab.datagen;

import me.mangorage.curiotiab.common.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static me.mangorage.curiotiab.common.Constants.MODID;

public class ItemTagGen extends ItemTagsProvider {
    public ItemTagGen(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        final var curiosCharmTag = tag(TagKey.create(Util.getTIABDeferredRegistry().getRegistryKey(), new ResourceLocation("curios", "tiab")));
        curiosCharmTag.add(Util.getTimeInABottle().get());
    }
}
