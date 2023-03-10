package me.mangorage.curiotiab.common.network.client;

import me.mangorage.curiotiab.common.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CUseTiab {
    private int x;
    private int y;
    private int z;

    public CUseTiab(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void encode(CUseTiab msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.x);
        buf.writeInt(msg.y);
        buf.writeInt(msg.z);
    }

    public static CUseTiab decode(FriendlyByteBuf buf) {
        return new CUseTiab(buf.readInt(), buf.readInt(), buf.readInt());
    }


    public static boolean vallidate(Level level, Player player, BlockPos pos) {
        return true;
    }

    public static void handle(CUseTiab msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            Level level = sender.getLevel();
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);

            if (level != null) {
                if (vallidate(level, sender, pos)) {
                    sender.sendSystemMessage(Component.literal("Recieved Key Stroke!"));
                    Util.handle(level, sender, pos);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

