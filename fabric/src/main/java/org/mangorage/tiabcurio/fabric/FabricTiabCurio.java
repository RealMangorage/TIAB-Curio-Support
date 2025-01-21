package org.mangorage.tiabcurio.fabric;

import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import org.mangorage.tiab.common.api.ICommonTimeInABottleAPI;
import org.mangorage.tiabcurio.common.CommonTiabCurio;
import org.mangorage.tiabcurio.common.network.client.UseBottlePacket;
import org.mangorage.tiabcurio.common.network.core.Context;

public final class FabricTiabCurio extends CommonTiabCurio implements ModInitializer {

    public FabricTiabCurio() {
        super(player -> {
            var list = TrinketsApi.getTrinketComponent(player).orElseThrow().getEquipped(ICommonTimeInABottleAPI.COMMON_API.get().getRegistration().getTiabItem().asItem());
            if (list.isEmpty())
                return null;
            return list.getFirst().getB();
        });
    }

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getObjectShare().whenAvailable("tiab:api", (k, v) -> {
            if (v instanceof ICommonTimeInABottleAPI api) {
                api.registerItemSearch(getCurioItemStackSearch());
                TrinketsApi.registerTrinket(
                        api.getRegistration().getTiabItem().asItem(),
                        new BottleTrinket()
                );
            }
        });

        PayloadTypeRegistry.playC2S().register(
                UseBottlePacket.TYPE,
                StreamCodec.unit(new UseBottlePacket())
        );

        ServerPlayNetworking.registerGlobalReceiver(
                UseBottlePacket.TYPE,
                (payload, context) -> {
                    payload.handle(new Context(context.player(), state -> {}));
                }
        );
    }
}
