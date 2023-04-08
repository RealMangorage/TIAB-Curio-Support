package me.mangorage.curiotiab.client.commands;

import me.mangorage.curiotiab.client.config.CurioTiabClientConfig;
import me.mangorage.curiotiab.client.screens.ConfigurationScreen;
import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.ChatFormatting;
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
                            Minecraft.getInstance().tell(() -> ConfigurationScreen.open());
                            return 1;
                        }))
                .then(Commands.literal("reloadconfig")
                        .executes(stack -> {
                            CurioTiabClientConfig.reload();
                            Translatable.COMMAND_RELOAD_CLIENT.sendSystemMessage(Minecraft.getInstance().player, ChatFormatting.GREEN);
                            return 1;
                        })));
    }
}
