package org.mangorage.tiabcurio.forge;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import org.mangorage.tiabcurio.common.core.Constants;
import org.mangorage.tiabcurio.common.network.core.Context;
import org.mangorage.tiabcurio.common.network.client.UseBottlePacket;

@Mod(Constants.MOD_ID)
public class ForgeTiabCurio {

    Channel<CustomPacketPayload> channel = ChannelBuilder
            .named("")
            .payloadChannel()
            .any()
            .serverbound()
            .add(
                    UseBottlePacket.TYPE,
                    StreamCodec.unit(new UseBottlePacket()),
                    (a, b) -> {
                        a.handle(() -> new Context() {
                            @Override
                            public ServerPlayer getSender() {
                                return b.getSender();
                            }

                            @Override
                            public void setPacketHandled(boolean flag) {
                                b.setPacketHandled(flag);
                            }
                        });
                    }
            )
            .build();

    public ForgeTiabCurio() {
    }
}
