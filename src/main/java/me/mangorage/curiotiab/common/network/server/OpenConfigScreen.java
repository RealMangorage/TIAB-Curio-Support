package me.mangorage.curiotiab.common.network.server;

import me.mangorage.curiotiab.client.screens.ConfigurationScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenConfigScreen {
    private static OpenConfigScreen INSTANCE = new OpenConfigScreen();
    public static OpenConfigScreen getInstance(FriendlyByteBuf buf) {return INSTANCE;}
    public static OpenConfigScreen getInstance() {return INSTANCE;}

    public static void handle(OpenConfigScreen msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide() == LogicalSide.CLIENT)
            ConfigurationScreen.open();

        ctx.get().setPacketHandled(true);
    }
}
