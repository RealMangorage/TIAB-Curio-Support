package me.mangorage.curiotiab.common;

import com.haoict.tiab.registries.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Constants {
    public final static String MODID = "curiotiab";
    public final static String SLOT_KEY = "curios.identifier.tiab";
    public final static DeferredRegister TIAB_REGISTRY = ItemRegistry.ITEMS;
    public final static RegistryObject<Item> TIAB_ITEM = ItemRegistry.timeInABottleItem;
}
