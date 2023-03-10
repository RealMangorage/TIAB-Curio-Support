package me.mangorage.curiotab.datagen;

import com.haoict.tiab.Tiab;
import com.haoict.tiab.registries.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTagGen extends ItemTagsProvider {
    public ItemTagGen(DataGenerator p_126530_, BlockTagsProvider p_126531_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        final var curiosCharmTag = tag(TagKey.create(ItemRegistry.ITEMS.getRegistryKey(), new ResourceLocation("curios", "tiab")));
        curiosCharmTag.add(ItemRegistry.timeInABottleItem.get());
    }
}
