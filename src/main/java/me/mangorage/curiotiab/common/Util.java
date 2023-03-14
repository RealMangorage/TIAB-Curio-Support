package me.mangorage.curiotiab.common;

import com.haoict.tiab.registries.ItemRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import java.util.Optional;

public class Util {
    public static Optional<SlotResult> getTiabSlot(Player player) {
        return CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.timeInABottleItem.get());
    }
    public static ItemStack getTiabCurioItemStack(Player player) {
        return getTiab(getTiabSlot(player));
    }
    public static ItemStack getTiab(Optional<SlotResult> slotResult) {
        if (slotResult.isPresent())
            return slotResult.get().stack();

        return ItemStack.EMPTY;
    }
}
