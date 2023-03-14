package me.mangorage.curiotiab.common.network.client;

import com.haoict.tiab.registries.ItemRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;
import java.util.function.Supplier;

public class CUseTiab {
    public CUseTiab() {}
    public CUseTiab(FriendlyByteBuf buf) {}
    public static void encode(CUseTiab msg, FriendlyByteBuf buf) {}
    public static void handle(CUseTiab msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            ServerLevel level = player.getLevel();

            if (level != null) {
                HitResult result = player.pick(player.getReachDistance(),0, true);
                if (result instanceof BlockHitResult blockHitResult) {
                    Optional<SlotResult> slotResult = CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.timeInABottleItem.get());

                    if (slotResult.get() != null) {
                        ItemStack stack = slotResult.get().stack();

                        UseOnContext context = new UseOnContext(level, player, InteractionHand.MAIN_HAND, stack, blockHitResult);
                        stack.useOn(context);
                    }
                }

            }
        });
        ctx.get().setPacketHandled(true);
    }
}

