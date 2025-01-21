package org.mangorage.tiabcurio.neoforge;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.mangorage.tiab.common.api.ICommonTimeInABottleAPI;
import org.mangorage.tiabcurio.common.CommonTiabCurio;
import org.mangorage.tiabcurio.common.core.Constants;
import org.mangorage.tiabcurio.common.network.client.UseBottlePacket;
import org.mangorage.tiabcurio.common.network.core.Context;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicReference;

@Mod(Constants.MOD_ID)
public final class NeoForgeTiabCurio extends CommonTiabCurio {

    public NeoForgeTiabCurio(IEventBus modBus) {
        super(player -> {
            AtomicReference<ItemStack> found = new AtomicReference<>(null);

            CuriosApi.getCuriosInventory(player).ifPresent(h -> {
                h.findFirstCurio(ICommonTimeInABottleAPI.COMMON_API.get().getRegistration().getTiabItem().asItem()).ifPresent(r -> {
                    found.set(r.stack());
                });
            });

            return found.get();
        });

        ICommonTimeInABottleAPI.COMMON_API.get().registerItemSearch(getCurioItemStackSearch());
        modBus.addListener(this::onNetwork);
    }

    public void onNetwork(final RegisterPayloadHandlersEvent event) {
        event.registrar(Constants.PACKET_VERSION)
                .playToServer(
                        UseBottlePacket.TYPE,
                        StreamCodec.unit(new UseBottlePacket()),
                        (p, c) -> p.handle(new Context((ServerPlayer) c.player(), state -> {}))
                );
    }
}
