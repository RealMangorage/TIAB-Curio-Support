package me.mangorage.curiotab.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static me.mangorage.curiotiab.common.core.Constants.MODID;

public class BlockTagsGen extends BlockTagsProvider {


    public BlockTagsGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return MODID + " Block Tags Provider";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
