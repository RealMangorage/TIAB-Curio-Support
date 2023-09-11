package me.mangorage.curiotiab.common.core;

import com.magorage.tiab.api.ITimeInABottleAPI;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import java.util.*;

import static me.mangorage.curiotiab.common.core.Constants.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class Util {
    private static ITimeInABottleAPI API;
    public static void setAPI(ITimeInABottleAPI api) {
        if (API != null) return;
        API = api;
    }

    public static Optional<SlotResult> getTiabSlotResult(Player player) {
        return CuriosApi.getCuriosHelper().findFirstCurio(player, API.getRegistryObject().get());
    }
    public static ItemStack getTiabCurioItemStack(Player player) {
        return getTiab(getTiabSlotResult(player));
    }
    public static ItemStack getTiab(Optional<SlotResult> slotResult) {
        if (slotResult.isPresent()) {
            ItemStack stack = slotResult.get().stack();
            if (stack.is(API.getRegistryObject().get()))
                return stack;
        }

        return ItemStack.EMPTY;
    }

    public static boolean checkMajor(String version, ArtifactVersion art) {
        try {
            final var v = new DefaultArtifactVersion(version);
            return v.getMajorVersion() >= art.getMajorVersion();
        } catch(Exception e) {
            return false;
        }
    }

    public static void sendStatusMessage(Player Player, String message) {
        Player.displayClientMessage(Component.literal(message), true);
    }
}
