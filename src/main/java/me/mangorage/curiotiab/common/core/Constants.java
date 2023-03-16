package me.mangorage.curiotiab.common.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class Constants {
    public final static String MODID = "curiotiab";
    public final static String TIAB_MODID = "tiab";
    public final static Item TIAB_ITEM;

    static {
        TIAB_ITEM = ForgeRegistries.ITEMS.getValue(new ResourceLocation(TIAB_MODID, "time_in_a_bottle"));
    }
}
