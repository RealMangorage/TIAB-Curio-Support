package me.mangorage.curiotiab.client;

import me.mangorage.curiotiab.common.network.NetworkHandler;
import me.mangorage.curiotiab.common.network.client.CUseTiab;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import static me.mangorage.curiotiab.common.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MODID, bus = MOD, value = Dist.CLIENT)
public class ClientRegistry {
    public static KeyMapping useTiab =
            new KeyMapping("key.curiotiab.use", org.lwjgl.glfw.GLFW.GLFW_KEY_G, "key.categories.misc");

    @SubscribeEvent
    public static void registerKeys(final RegisterKeyMappingsEvent evt) {
        evt.register(useTiab);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = FORGE, value = Dist.CLIENT)
    class Forge {
        @SubscribeEvent
        public static void onKeyInput(TickEvent.ClientTickEvent evt) {
            if (evt.phase != TickEvent.Phase.END) {
                return;
            }

            Minecraft mc = Minecraft.getInstance();
            if (ClientRegistry.useTiab.consumeClick() && mc.level != null) {
                HitResult result = mc.player.pick(mc.player.getReachDistance(), mc.getPartialTick(), true);

                if (result instanceof BlockHitResult hit)
                    NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CUseTiab(hit));
            }
        }
    }
}
