package me.mangorage.curiotiab.common.network.client;

import me.mangorage.curiotiab.common.core.Constants;
import me.mangorage.curiotiab.common.core.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class UseTiabPacket {
    private final static UseTiabPacket INSTANCE = new UseTiabPacket();
    public static UseTiabPacket getInstance(FriendlyByteBuf buf) {
        return INSTANCE;
    }
    public static UseTiabPacket getInstance() {
        return INSTANCE;
    }

    private UseTiabPacket() {}
    public static void handle(UseTiabPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        ServerLevel level = player.getLevel();

        if (level != null) {
            ItemStack TiabItemStack = Util.getTiabCurioItemStack(player);
            if (TiabItemStack != ItemStack.EMPTY) {
                HitResult result = player.pick(player.getReachDistance(), 0, true);
                if (result instanceof BlockHitResult blockHitResult) {
                    UseOnContext context = new UseOnContext(level, player, InteractionHand.MAIN_HAND, TiabItemStack, blockHitResult);
                    TiabItemStack.useOn(context);
                }
            }
        }
        ctx.get().setPacketHandled(true);
    }
}

