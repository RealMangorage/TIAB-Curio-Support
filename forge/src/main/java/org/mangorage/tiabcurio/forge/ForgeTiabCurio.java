package org.mangorage.tiabcurio.forge;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.GatherLoginConfigurationTasksEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.ConnectionType;
import net.minecraftforge.network.NetworkContext;
import net.minecraftforge.network.SimpleChannel;
import net.minecraftforge.network.tasks.ForgeNetworkConfigurationHandler;
import org.mangorage.tiabcurio.common.Constants;
import org.mangorage.tiabcurio.common.network.Context;
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
