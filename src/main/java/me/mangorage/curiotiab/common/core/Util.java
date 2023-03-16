package me.mangorage.curiotiab.common.core;

import com.haoict.tiab.items.AbstractTiabItem;
import me.mangorage.curiotiab.common.core.Constants;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import java.util.Optional;

public class Util {
    public static Optional<SlotResult> getTiabSlotResult(Player player) {
        return CuriosApi.getCuriosHelper().findFirstCurio(player, Constants.TIAB_ITEM);
    }
    public static ItemStack getTiabCurioItemStack(Player player) {
        return getTiab(getTiabSlotResult(player));
    }
    public static ItemStack getTiab(Optional<SlotResult> slotResult) {
        if (slotResult.isPresent()) {
            ItemStack stack = slotResult.get().stack();
            if (stack.getItem() instanceof AbstractTiabItem)
                return stack;
        }

        return ItemStack.EMPTY;
    }
}
