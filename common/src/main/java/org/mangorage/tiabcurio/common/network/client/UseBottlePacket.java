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
import org.mangorage.tiabcurio.common.CommonTiabCurio;
import org.mangorage.tiabcurio.common.core.Constants;
import org.mangorage.tiabcurio.common.network.core.Context;

public record UseBottlePacket() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<UseBottlePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "use_bottle"));

    public void handle(Context ctx) {
        ServerPlayer player = ctx.player();
        ServerLevel level = player.serverLevel();

        if (level != null) {
            ItemStack TiabItemStack = CommonTiabCurio.getCurioItemStackSearch().findItem(player);
            if (TiabItemStack != ItemStack.EMPTY) {
                HitResult result = player.pick(player.getAttributes().getValue(Attributes.BLOCK_INTERACTION_RANGE), 0, true);
                if (result instanceof BlockHitResult blockHitResult) {
                    UseOnContext context = new UseOnContext(level, player, player.getUsedItemHand(), TiabItemStack, blockHitResult);
                    TiabItemStack.useOn(context);
                }
            }
        }
        ctx.handled().accept(true);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
