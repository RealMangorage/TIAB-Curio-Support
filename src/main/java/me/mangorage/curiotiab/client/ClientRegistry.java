package me.mangorage.curiotiab.client;

import me.mangorage.curiotiab.client.commands.ModifyCurioGuiCommand;
import me.mangorage.curiotiab.common.network.NetworkHandler;
import me.mangorage.curiotiab.common.network.client.UseTiabPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import static com.mojang.blaze3d.platform.InputConstants.KEY_G;
import static com.mojang.blaze3d.platform.InputConstants.KEY_H;
import static me.mangorage.curiotiab.client.commands.ModifyCurioGuiCommand.RESET;
import static me.mangorage.curiotiab.client.commands.ModifyCurioGuiCommand.SAVE;
import static me.mangorage.curiotiab.common.core.Constants.MODID;
import static me.mangorage.curiotiab.common.core.Translatable.*;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MODID, bus = MOD, value = Dist.CLIENT)
public class ClientRegistry {
    public final static KeyMapping useTiab = new KeyMapping(USE_KEY.getKey(), KEY_G, CATEGORY_KEY.getKey());
    public final static KeyMapping hideGUI = new KeyMapping(TOGGLE_KEY.getKey(), KEY_H, CATEGORY_KEY.getKey());

    @SubscribeEvent
    public static void registerKeys(final RegisterKeyMappingsEvent evt) {
        evt.register(useTiab);
        evt.register(hideGUI);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(final RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(MODID, CurioTiabHudOverlay.getInstance());
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = FORGE, value = Dist.CLIENT)
    class Forge {
        @SubscribeEvent
        public static void onKeyInput(final TickEvent.ClientTickEvent evt) {
            if (evt.phase != TickEvent.Phase.END)
                return;

            Minecraft mc = Minecraft.getInstance();
            if (ClientRegistry.useTiab.consumeClick() && mc.level != null)
                NetworkHandler.NETWORK_CHANNEL.send(PacketDistributor.SERVER.noArg(), UseTiabPacket.getInstance());

            if (ClientRegistry.hideGUI.consumeClick())
                CurioTiabHudOverlay.getInstance().toggleOverlay();
        }

        @SubscribeEvent
        public static void commandRegister(final RegisterCommandsEvent event) {
            event.getDispatcher().register(
                    Commands.literal("modifyCurioTiabGui")
                            .executes(ModifyCurioGuiCommand::toggleCommand)
                            .then(Commands.literal(SAVE)
                                    .executes(ModifyCurioGuiCommand::saveCommand)
                            ).then(Commands.literal(RESET)
                                    .executes(ModifyCurioGuiCommand::resetCommand)
                            )
            );

        }

        @SubscribeEvent
        public static void screenEvent(final ScreenEvent.Init event) {
            event.addListener(CurioTiabHudOverlay.getInstance());
        }
    }
}
