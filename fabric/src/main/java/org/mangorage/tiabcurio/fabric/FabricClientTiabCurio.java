package org.mangorage.tiabcurio.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import org.mangorage.tiabcurio.common.client.ClientConstants;
import org.mangorage.tiabcurio.common.network.client.UseBottlePacket;

public class FabricClientTiabCurio implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(ClientConstants.USE_BOTTLE);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level != null) {
                if (ClientConstants.USE_BOTTLE.consumeClick())
                    mc.getConnection().send(new ServerboundCustomPayloadPacket(new UseBottlePacket()));
            }
        });
    }
}
