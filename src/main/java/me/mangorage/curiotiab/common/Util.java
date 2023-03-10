package me.mangorage.curiotiab.common;

import com.haoict.tiab.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

public class Util {
    public static void handle(Level level, Player player, BlockPos pos) {
        Optional<SlotResult> slotResult = CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.timeInABottleItem.get());

        if (slotResult.get() != null) {
            ItemStack stack = slotResult.get().stack();

            UseOnContext context = new UseOnContext(level, player, InteractionHand.MAIN_HAND, stack, new BlockHitResult(new Vec3(0,0,0), Direction.DOWN, pos, false));
            ItemRegistry.timeInABottleItem.get().useOn(context);
        }

    }
}
