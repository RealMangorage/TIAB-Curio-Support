package me.mangorage.curiotiab.common.core;

import com.haoict.tiab.Tiab;
import com.haoict.tiab.common.core.ItemRegistry;
import com.haoict.tiab.common.items.AbstractTiabItem;
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

    public final static Optional<SlotResult> getTiabSlotResult(Player player) {
        return CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.timeInABottleItem.get());
    }
    public final static ItemStack getTiabCurioItemStack(Player player) {
        return getTiab(getTiabSlotResult(player));
    }
    public final static ItemStack getTiab(Optional<SlotResult> slotResult) {
        if (slotResult.isPresent()) {
            ItemStack stack = slotResult.get().stack();
            if (stack.getItem() instanceof AbstractTiabItem)
                return stack;
        }

        return ItemStack.EMPTY;
    }

    public final static boolean checkMajor(String version, ArtifactVersion art) {
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
