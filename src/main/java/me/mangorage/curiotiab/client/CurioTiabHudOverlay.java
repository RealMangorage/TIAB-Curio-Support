package me.mangorage.curiotiab.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.mangorage.curiotiab.common.Util;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CurioTiabHudOverlay implements IGuiOverlay {
    private final static CurioTiabHudOverlay INSTANCE = new CurioTiabHudOverlay();
    public final static CurioTiabHudOverlay getInstance() {return INSTANCE;}
    private final AtomicBoolean HIDDEN = new AtomicBoolean(true);
    public void toggleOverlay() {
        HIDDEN.set(!HIDDEN.get());
    }
    private CurioTiabHudOverlay() {}
    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        if (HIDDEN.get())
            return;

        int x = screenWidth / 2;
        int y = screenHeight / 2;
        int xOffset = (x / 100) * -100;
        AtomicInteger yOffset = new AtomicInteger((y / 100) * -100);

        Player player = Minecraft.getInstance().player;
        Font font = Minecraft.getInstance().font;


        if (player != null) {
            ItemStack TIAB = Util.getTiabCurioItemStack(player);
            if (TIAB != ItemStack.EMPTY) {
                List<Component> Lines = TIAB.getTooltipLines(player, TooltipFlag.Default.NORMAL);

                Lines.remove(0);
                Lines.remove(0);

                Lines.add(0, Component.literal("Time in a Bottle Curio").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));

                Lines.forEach((component) -> {
                    GuiComponent.drawString(poseStack, font, component, x + xOffset, y + yOffset.get(), 128);
                    yOffset.set(yOffset.get() + 10);
                });
            }
        }
    }
}