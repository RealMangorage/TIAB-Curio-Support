package me.mangorage.curiotiab.common.network.client;

import com.haoict.tiab.registries.ItemRegistry;
import me.mangorage.curiotiab.common.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;
import java.util.function.Supplier;

public class CUseTiab {
    private Vec3 vec3;
    private Direction direction;
    private BlockPos pos;
    private boolean inside;

    public CUseTiab(Vec3 vec3, Direction direction, BlockPos pos, boolean inside ) {
        this.vec3 = vec3;
        this.direction = direction;
        this.pos = pos;
        this.inside = inside;
    }

    public static void encode(CUseTiab msg, FriendlyByteBuf buf) {
        buf.writeDouble(msg.vec3.x);
        buf.writeDouble(msg.vec3.y);
        buf.writeDouble(msg.vec3.z);

        buf.writeInt(Util.getIDFromDirection(msg.direction));

        buf.writeInt(msg.pos.getX());
        buf.writeInt(msg.pos.getY());
        buf.writeInt(msg.pos.getZ());

        buf.writeBoolean(msg.inside);



    }

    public static CUseTiab decode(FriendlyByteBuf buf) {
        return new CUseTiab(
                new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble()),
                Util.getDirectionFromID(buf.readInt()),
                new BlockPos(buf.readInt(), buf.readInt(), buf.readInt()),
                buf.readBoolean()
        );
    }


    public static boolean validate(Player player, BlockPos pos) {
        if (Util.getDistanceBetweenPoints(player.getOnPos(), pos) > player.getReachDistance()) {
            return false;
        }
        return true;
    }

    public static void handle(CUseTiab msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            Level level = player.getLevel();

            if (level != null) {
                if (validate(player, msg.pos)) {
                    Optional<SlotResult> slotResult = CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.timeInABottleItem.get());

                    if (slotResult.get() != null) {
                        ItemStack stack = slotResult.get().stack();

                        UseOnContext context = new UseOnContext(level, player, InteractionHand.MAIN_HAND, stack, new BlockHitResult(msg.vec3, msg.direction, msg.pos, msg.inside));
                        ItemRegistry.timeInABottleItem.get().useOn(context);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

