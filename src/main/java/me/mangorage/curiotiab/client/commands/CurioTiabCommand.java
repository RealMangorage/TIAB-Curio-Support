package me.mangorage.curiotiab.client.commands;

import me.mangorage.curiotiab.client.screens.ConfigurationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import static me.mangorage.curiotiab.common.core.Constants.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CurioTiabCommand {
    @SubscribeEvent
    public static void register(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("curiotiab")
                .then(Commands.literal("configureOverlay")
                        .executes((stack) -> {
                            ConfigurationScreen.openConsumer((screen) -> {
                                Minecraft.getInstance().setScreen(screen);
                            });
                            return 1;
                        })));
    }
}
