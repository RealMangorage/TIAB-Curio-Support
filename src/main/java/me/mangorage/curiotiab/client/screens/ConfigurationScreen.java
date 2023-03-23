package me.mangorage.curiotiab.client.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import me.mangorage.curiotiab.client.screens.overlays.CurioTiabHudOverlay;
import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import java.awt.event.MouseEvent;
import java.util.function.Supplier;

public class ConfigurationScreen extends Screen {
    private final static Supplier<ConfigurationScreen> SUPPLIER_INSTANCE = () -> new ConfigurationScreen();
    public final static ConfigurationScreen getInstance() {
        return SUPPLIER_INSTANCE.get();
    }

    private boolean hiddenMode = false;
    private boolean leftClickedRecently = false;
    private int ticks = 0;
    private int x, y = 5;

    private ConfigurationScreen() {
        super(Translatable.SCREEN_CONFIG.get());
    }

    @Override
    public void tick() {
        ticks++;
        if (ticks % 5 == 0)
            leftClickedRecently = false;

        super.tick();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (pButton == MouseEvent.NOBUTTON)
            leftClickedRecently = true;

        this.x = (int) pMouseX;
        this.y = (int) pMouseY;
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (pButton == MouseEvent.BUTTON1)
            reset();
        if (pButton == MouseEvent.NOBUTTON && leftClickedRecently)
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

        Minecraft.getInstance().setScreen(ConfigurationScreen.getInstance());

        this.x = 5;
        this.y = 5;
    }

    @Override
    protected void init() {
        hiddenMode = CurioTiabHudOverlay.isHidden();
        if (!hiddenMode)
            CurioTiabHudOverlay.getInstance().toggleOverlay(); // Turn it off!

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
