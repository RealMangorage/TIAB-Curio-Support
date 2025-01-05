package org.mangorage.tiabcurio.fabric.trinket;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.mangorage.tiab.common.api.ICommonTimeInABottleAPI;

public class BottleTrinket implements Trinket {
    public BottleTrinket() {
        TrinketsApi.registerTrinket(
                ICommonTimeInABottleAPI.COMMON_API.get().getRegistration().getTiabItem().asItem(),
                this
        );
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return slot.getId().equals("tiab");
    }
}
