package me.mangorage.curiotiab.common;

import com.haoict.tiab.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Util {


    private final static Map<Integer, Direction> DIRECTION_MAP = new HashMap<>();
    private final static Map<Direction, Integer> DIRECTION_MAP_BACKWARDS = new HashMap<>();
    private static boolean loaded = false;

    private static void handleDirection(int i, Direction direction) {
        DIRECTION_MAP.put(i, direction);
        DIRECTION_MAP_BACKWARDS.put(direction, i);
    }

    public static Direction getDirectionFromID(int id) {
        return DIRECTION_MAP.get(id);
    }

    public static int getIDFromDirection(Direction direction) {
        return DIRECTION_MAP_BACKWARDS.get(direction);
    }

    public static void load() {
        if (loaded)
            return;

        loaded = true;
        handleDirection(0, Direction.UP);
        handleDirection(1, Direction.DOWN);
        handleDirection(2, Direction.EAST);
        handleDirection(3, Direction.WEST);
        handleDirection(4, Direction.SOUTH);
        handleDirection(5, Direction.NORTH);
    }

    public static double getDistanceBetweenPoints(BlockPos A, BlockPos B) {
        double deltaX = A.getX() - B.getX();
        double deltaY = A.getY() - B.getY();
        double deltaZ = A.getZ() - B.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }


    public static DeferredRegister getTIABDeferredRegistry() {
        return ItemRegistry.ITEMS;
    }
    public static RegistryObject<Item> getTimeInABottle() {
        return ItemRegistry.timeInABottleItem;
    }
}
