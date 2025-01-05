package org.mangorage.tiabcurio.neoforge.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.mangorage.tiabcurio.common.network.core.Context;
import org.mangorage.tiabcurio.common.network.core.IHandler;

public final class Handler {
    public static <T extends CustomPacketPayload & IHandler> void handle(T packet, IPayloadContext context) {
        packet.handle(() -> new Context() {
            @Override
            public ServerPlayer getSender() {
                return (ServerPlayer) context.player();
            }

            @Override
            public void setPacketHandled(boolean flag) {

            }
        });
    }
}
