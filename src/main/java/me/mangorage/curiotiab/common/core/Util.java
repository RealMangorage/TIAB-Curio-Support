package me.mangorage.curiotiab.common.core;

import com.haoict.tiab.items.AbstractTiabItem;
import me.mangorage.curiotiab.common.core.Constants;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Util {
    public static Optional<SlotResult> getTiabSlotResult(Player player) {
        return CuriosApi.getCuriosHelper().findFirstCurio(player, Constants.TIAB_ITEM);
    }
    public static ItemStack getTiabCurioItemStack(Player player) {
        return getTiab(getTiabSlotResult(player));
    }
    public static ItemStack getTiab(Optional<SlotResult> slotResult) {
        if (slotResult.isPresent()) {
            ItemStack stack = slotResult.get().stack();
            if (stack.getItem() instanceof AbstractTiabItem)
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
}
