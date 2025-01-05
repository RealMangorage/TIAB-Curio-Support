package org.mangorage.tiabcurio.common.network.client;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.mangorage.tiabcurio.common.CommonTIabCurio;
import org.mangorage.tiabcurio.common.core.Constants;
import org.mangorage.tiabcurio.common.network.core.Context;
import org.mangorage.tiabcurio.common.network.core.IHandler;

import java.util.function.Supplier;

public record UseBottlePacket() implements CustomPacketPayload, IHandler {
    public static final ResourceLocation CHANNEL_NAME = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "channel_registration");
    public static final CustomPacketPayload.Type<UseBottlePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "use_bottle"));

    @Override
    public void handle(Supplier<Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        ServerLevel level = player.serverLevel();

        if (level != null) {
            ItemStack TiabItemStack = CommonTIabCurio.getCurioItemStackSearch().findItem(player);
            if (TiabItemStack != ItemStack.EMPTY) {
                HitResult result = player.pick(player.getAttributes().getValue(Attributes.BLOCK_INTERACTION_RANGE), 0, true);
                if (result instanceof BlockHitResult blockHitResult) {
                    UseOnContext context = new UseOnContext(level, player, player.getUsedItemHand(), TiabItemStack, blockHitResult);
                    TiabItemStack.useOn(context);
                }
            }
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
