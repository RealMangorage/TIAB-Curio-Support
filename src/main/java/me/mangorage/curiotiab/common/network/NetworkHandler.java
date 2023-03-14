package me.mangorage.curiotiab.common.network;

import me.mangorage.curiotiab.common.network.client.CUseTiab;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.checkerframework.checker.units.qual.C;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static me.mangorage.curiotiab.common.Constants.MODID;

public class NetworkHandler {
    private static final String PTC_VERSION = "2";
    public static SimpleChannel INSTANCE;
    private static int id = 0;

    public static void register() {
        INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(MODID, "main"))
                .networkProtocolVersion(() -> PTC_VERSION)
                .clientAcceptedVersions(PTC_VERSION::equals)
                .serverAcceptedVersions(PTC_VERSION::equals)
                .simpleChannel();

        //Client Packets
        register(CUseTiab.class, CUseTiab::encode, CUseTiab::new, CUseTiab::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    private static <M> void register(Class<M> messageType, BiConsumer<M, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, M> decoder, BiConsumer<M, Supplier<NetworkEvent.Context>> messageConsumer, Optional<NetworkDirection> direction) {
        INSTANCE.registerMessage(id++, messageType, encoder, decoder, messageConsumer, direction);
    }

    private static <M> void register(Class<M> messageType, BiConsumer<M, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, M> decoder, BiConsumer<M, Supplier<NetworkEvent.Context>> messageConsumer) {
        register(messageType, encoder, decoder, messageConsumer, Optional.empty());
    }
}
