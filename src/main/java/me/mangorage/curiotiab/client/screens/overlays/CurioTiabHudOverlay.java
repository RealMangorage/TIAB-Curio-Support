package me.mangorage.curiotiab.client.screens.overlays;

import com.haoict.tiab.items.TimeInABottleItem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.mangorage.curiotiab.client.config.CurioTiabClientConfig;
import me.mangorage.curiotiab.common.core.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CurioTiabHudOverlay implements IGuiOverlay {
    private final static CurioTiabHudOverlay INSTANCE = new CurioTiabHudOverlay();

    public final static CurioTiabHudOverlay getInstance() {
        return INSTANCE;
    }

    public final static void setHidden(boolean value) {
        getInstance().HIDDEN.set(value);
    }

    public final static boolean isHidden() {
        return getInstance().HIDDEN.get() ? true : false;
    }

    private final AtomicBoolean HIDDEN = new AtomicBoolean(true);
    private int x, y = 5;
    private boolean check = true;

    private CurioTiabHudOverlay() {}

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        CurioTiabClientConfig.POSX.set(x);
        CurioTiabClientConfig.POSY.set(y);

        CurioTiabClientConfig.POSX.save();
        CurioTiabClientConfig.POSY.save();
    }

    public void toggleOverlay() {
        HIDDEN.set(!HIDDEN.get());
    }

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        if (HIDDEN.get())
            return;

        if (check) {
            this.x = CurioTiabClientConfig.POSX.get();
            this.y = CurioTiabClientConfig.POSY.get();
            check = !check;
        }

        AtomicInteger yOffset = new AtomicInteger(0);
        Player player = Minecraft.getInstance().player;
        Font font = Minecraft.getInstance().font;

        if (player != null) {
            ItemStack TIAB = Util.getTiabCurioItemStack(player);
            if (TIAB != ItemStack.EMPTY) {
                List<Component> Lines = new ArrayList<>();
                Lines.add(0, Component.literal("Time in a Bottle Curio").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));

                TIAB.getTooltipLines(player, TooltipFlag.Default.NORMAL).forEach((component -> {
                    if (component.getString().contains("Stored time") || component.getString().contains("Total accumulated time"))
                        Lines.add(component);
                }));


                Lines.forEach((component) ->
                        GuiComponent.drawString(poseStack, font, component, this.x, this.y + yOffset.getAndAdd(10), 128));
            }
        }
    }
}