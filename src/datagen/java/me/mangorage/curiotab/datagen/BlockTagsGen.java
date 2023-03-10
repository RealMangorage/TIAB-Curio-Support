package me.mangorage.curiotab.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static me.mangorage.curiotiab.common.Constants.MODID;

public class BlockTagsGen extends BlockTagsProvider {
    public BlockTagsGen(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }
}
