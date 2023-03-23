package me.mangorage.curiotiab.common.network.server;

import me.mangorage.curiotiab.client.screens.ConfigurationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenConfigScreen {
    private static OpenConfigScreen INSTANCE = new OpenConfigScreen();
    public static OpenConfigScreen getInstance(FriendlyByteBuf buf) {return INSTANCE;}
    public static OpenConfigScreen getInstance() {return INSTANCE;}

    public static void handle(OpenConfigScreen msg, Supplier<NetworkEvent.Context> ctx) {
        if (Minecraft.getInstance() != null)
            Minecraft.getInstance().setScreen(ConfigurationScreen.getInstance());

        ctx.get().setPacketHandled(true);
    }
}