package me.mangorage.curiotiab.client.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import cpw.mods.util.Lazy;
import me.mangorage.curiotiab.client.config.CurioTiabClientConfig;
import me.mangorage.curiotiab.client.screens.overlays.CurioTiabHudOverlay;
import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.awt.event.MouseEvent;

public class ConfigurationScreen extends Screen {
    private final static ConfigurationScreen INSTANCE = new ConfigurationScreen();
    public final static ConfigurationScreen getInstance() {
        return INSTANCE;
    }

    private boolean hiddenMode = false;
    private int x, y = 5;
    private ConfigurationScreen() {
        super(Translatable.SCREEN_CONFIG.get());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        this.x = (int) pMouseX;
        this.y = (int) pMouseY;
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (pButton == MouseEvent.BUTTON1)
            reset();
        if (pButton == MouseEvent.BUTTON2)
            save();
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        int id = 1;
        if (minecraft != null && minecraft.player != null) {
            font.draw(pPoseStack, Translatable.SCREEN_DRAG.get().withStyle(ChatFormatting.GOLD), x, y, 128);
            font.draw(pPoseStack, Translatable.SCREEN_CLOSE.get().withStyle(ChatFormatting.GOLD), x, y + (10 * id++), 128);
            font.draw(pPoseStack, Translatable.SCREEN_SAVE.get().withStyle(ChatFormatting.GOLD), x, y + (10 * id++), 128);
            font.draw(pPoseStack, Translatable.SCREEN_RESET.get().withStyle(ChatFormatting.GOLD), x, y + (10 * id), 128);
        }
    }

    @Override
    public void onClose() {
        CurioTiabHudOverlay.setHidden(hiddenMode);
        Minecraft.getInstance().setScreen(null);
    }

    public void open() {
        hiddenMode = CurioTiabHudOverlay.isHidden();
        if (!hiddenMode)
            CurioTiabHudOverlay.getInstance().toggleOverlay(); // Turn it off!

        Minecraft.getInstance().setScreen(INSTANCE);

        this.x = 5;
        this.y = 5;
    }

    private void save() {
        minecraft.player.sendSystemMessage(Translatable.MSG_SAVED.get().withStyle(ChatFormatting.GREEN));
        CurioTiabHudOverlay.getInstance().setPosition(x, y);
    }

    private void reset() {
        minecraft.player.sendSystemMessage(Translatable.MSG_RESET.get().withStyle(ChatFormatting.GREEN));
        CurioTiabHudOverlay.getInstance().setPosition(5, 5);
        this.x = 5;
        this.y = 5;

    }
}
