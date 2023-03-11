package me.mangorage.curiotiab.common.network.client;

import com.haoict.tiab.registries.ItemRegistry;
import me.mangorage.curiotiab.common.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;
import java.util.function.Supplier;

public class CUseTiab {
    public BlockHitResult result;
    public CUseTiab(BlockHitResult result) {
        this.result = result;
    }

    public static void encode(CUseTiab msg, FriendlyByteBuf buf) {
        buf.writeBlockHitResult(msg.result);
    }

    public static CUseTiab decode(FriendlyByteBuf buf) {
        return new CUseTiab(buf.readBlockHitResult());
    }

    public static boolean validate(Player player, BlockPos pos) {
        if (player.getOnPos().distSqr(pos) > player.getReachDistance())
            return false;
        return true;
    }

    public static void handle(CUseTiab msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            Level level = player.getLevel();
            BlockPos pos = msg.result.getBlockPos();

            if (level != null) {
                if (validate(player, pos)) {

                    Optional<SlotResult> slotResult = CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.timeInABottleItem.get());


                    if (slotResult.get() != null) {
                        ItemStack stack = slotResult.get().stack();

                        UseOnContext context = new UseOnContext(level, player, InteractionHand.MAIN_HAND, stack, msg.result);
                        Constants.TIAB_ITEM.get().useOn(context);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

