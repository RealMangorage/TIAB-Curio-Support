package org.mangorage.tiabcurio.fabric;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.mangorage.tiab.common.api.ICommonTimeInABottleAPI;

public class BottleTrinket implements Trinket {
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return stack.getItem() == ICommonTimeInABottleAPI.COMMON_API.get().getRegistration().getTiabItem().asItem();
    }
}
