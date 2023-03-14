package me.mangorage.curiotiab.client;

import me.mangorage.curiotiab.common.network.NetworkHandler;
import me.mangorage.curiotiab.common.network.client.CUseTiab;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import static com.mojang.blaze3d.platform.InputConstants.KEY_G;
import static com.mojang.blaze3d.platform.InputConstants.KEY_H;
import static me.mangorage.curiotiab.common.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MODID, bus = MOD, value = Dist.CLIENT)
public class ClientRegistry {
    public final static String CATEGORY_KEY = "key.categories.curiotiab";
    public final static String USE_KEY = "key.curiotiab.use";
    public final static String TOGGLE_KEY = "key.curiotiab.hide";
    public static KeyMapping useTiab = new KeyMapping(USE_KEY, KEY_G, CATEGORY_KEY);
    public static KeyMapping hideGUI = new KeyMapping(TOGGLE_KEY, KEY_H, CATEGORY_KEY);

    @SubscribeEvent
    public static void registerKeys(final RegisterKeyMappingsEvent evt) {
        evt.register(useTiab);
        evt.register(hideGUI);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(MODID, CurioTiabHudOverlay.getInstance());
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = FORGE, value = Dist.CLIENT)
    class Forge {
        @SubscribeEvent
        public static void onKeyInput(TickEvent.ClientTickEvent evt) {
            if (evt.phase != TickEvent.Phase.END) {
                return;
            }

            Minecraft mc = Minecraft.getInstance();
            if (ClientRegistry.useTiab.consumeClick() && mc.level != null)
                NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CUseTiab());

            if (ClientRegistry.hideGUI.consumeClick())
                CurioTiabHudOverlay.getInstance().toggleOverlay();
        }
    }
}
