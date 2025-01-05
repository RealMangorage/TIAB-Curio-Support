package org.mangorage.tiabcurio.neoforge.client;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.mangorage.tiabcurio.common.core.Constants;
import org.mangorage.tiabcurio.common.client.ClientConstants;
import org.mangorage.tiabcurio.common.network.client.UseBottlePacket;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class ClientRegistry {
    @SubscribeEvent
    public static void onKeyInput(final ClientTickEvent.Post evt) {

        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null) {
            if (ClientConstants.USE_BOTTLE.consumeClick())
                Minecraft.getInstance().getConnection().send(new UseBottlePacket());
        }
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class Mod {
        @SubscribeEvent
        public static void registryKeys(final RegisterKeyMappingsEvent event) {
            event.register(ClientConstants.USE_BOTTLE);
        }
    }
}
