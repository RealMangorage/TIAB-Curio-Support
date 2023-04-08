package me.mangorage.curiotiab.client.screens;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import me.mangorage.curiotiab.client.screens.overlays.CurioTiabHudOverlay;
import me.mangorage.curiotiab.common.core.Translatable;
import me.mangorage.curiotiab.common.core.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.mutable.MutableInt;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationScreen extends Screen {
    private List<Component> components = new ArrayList<>();
    private int maxWidth = 0;
    private boolean hiddenMode = false;
    private int x, y = 5;


    public static void open() {
        Minecraft.getInstance().setScreen(new ConfigurationScreen());
    }

    private ConfigurationScreen() {
        super(Translatable.SCREEN_CONFIG.get());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        int cX = (int) (pMouseX < 0 ? 0 : pMouseX);
        int cY = (int) (pMouseY < 0 ? 0 : pMouseY);

        this.x = Util.getClampedWidth(cX, minecraft.screen.width, maxWidth);
        this.y = Util.getClampedHeight(cY, minecraft.screen.height, font.lineHeight * components.size());
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (hasShiftDown()) {
            if (pKeyCode == InputConstants.KEY_R)
                reset();
            if (pKeyCode == InputConstants.KEY_S)
                save();
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (!minecraft.isRunning())
            return;


        int cX = Util.getClampedWidth(x, minecraft.screen.width, maxWidth);
        int cY = Util.getClampedHeight(y, minecraft.screen.height, font.lineHeight * components.size());
        renderBackground(pPoseStack);

        MutableInt finalID = new MutableInt(0);
        components.forEach(component ->
                drawString(pPoseStack, font, component, cX, cY + (font.lineHeight * (finalID.getAndAdd(1))), 128)
        );
    }

    @Override
    public void onClose() {
        CurioTiabHudOverlay.setHidden(hiddenMode);
        Translatable.SCREEN_CLOSED.sendSystemMessage(minecraft.player, ChatFormatting.GREEN);
        super.onClose();
    }

    public void updateComponents() {
        components.clear();
        components.add(Translatable.SCREEN_DRAG.get().withStyle(ChatFormatting.GOLD));
        components.add(Translatable.SCREEN_CLOSE.get().withStyle(ChatFormatting.GOLD));
        components.add(Translatable.SCREEN_SAVE.get().withStyle(ChatFormatting.GOLD));
        components.add(Translatable.SCREEN_RESET.get().withStyle(ChatFormatting.GOLD));
        this.maxWidth = Util.getMaxStringsWidth(components, font);
    }

    @Override
    protected void init() {
        hiddenMode = CurioTiabHudOverlay.isHidden();
        if (!hiddenMode)
            CurioTiabHudOverlay.getInstance().toggleOverlay(); // Turn it off!

        updateComponents();
        this.x = 5;
        this.y = 5;
    }

    private void save() {
        Translatable.MSG_SAVED.sendSystemMessage(minecraft.player, ChatFormatting.GREEN);
        CurioTiabHudOverlay.getInstance().setPosition(x, y);

    }

    private void reset() {
        Translatable.MSG_RESET.sendSystemMessage(minecraft.player, ChatFormatting.GREEN);
        CurioTiabHudOverlay.getInstance().setPosition(5, 5);
        this.x = 5;
        this.y = 5;
    }
}
