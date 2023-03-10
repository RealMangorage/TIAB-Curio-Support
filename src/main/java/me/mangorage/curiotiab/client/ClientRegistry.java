package me.mangorage.curiotiab.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.mangorage.curiotiab.common.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MODID, bus = MOD, value = Dist.CLIENT)
public class ClientRegistry {
    public static KeyMapping useTiab =
            new KeyMapping("key.curiotiab.use", org.lwjgl.glfw.GLFW.GLFW_KEY_G, "key.categories.misc");

    @SubscribeEvent
    public static void registerKeys(final RegisterKeyMappingsEvent evt) {
        evt.register(useTiab);
    }
}
