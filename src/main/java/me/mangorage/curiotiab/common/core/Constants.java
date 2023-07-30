package me.mangorage.curiotiab.common.core;

import com.haoict.tiab.api.TimeInABottleAPI;
import net.minecraft.world.item.Item;

public class Constants {
    public final static String MODID = "curiotiab";
    public final static String TIAB_MODID = TimeInABottleAPI.getModID();
    public final static Item TIAB_ITEM = TimeInABottleAPI.getRegistryItem().get();
}
