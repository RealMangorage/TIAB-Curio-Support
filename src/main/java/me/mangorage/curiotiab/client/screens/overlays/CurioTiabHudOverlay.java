package me.mangorage.curiotiab.client.screens.overlays;

import com.magorage.tiab.api.ITimeInABottleAPI;
import me.mangorage.curiotiab.client.config.CurioTiabClientConfig;
import me.mangorage.curiotiab.common.core.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CurioTiabHudOverlay implements IGuiOverlay {
    public enum Mode {
        TOTAL,
        CURRENT,
        BOTH
    }
    private final static CurioTiabHudOverlay INSTANCE = new CurioTiabHudOverlay();

    public static CurioTiabHudOverlay getInstance() {
        return INSTANCE;
    }

    public static void setHidden(boolean value) {
        getInstance().HIDDEN.set(value);
    }

    public static boolean isHidden() {
        return getInstance().HIDDEN.get();
    }

    private final AtomicBoolean HIDDEN = new AtomicBoolean(true);
    private ITimeInABottleAPI API = null;
    private int x, y = 5;
    private boolean check = true;

    private CurioTiabHudOverlay() {
    }

    public void setAPI(ITimeInABottleAPI api) {
        if (this.API != null) return;
        this.API = api;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        CurioTiabClientConfig.setPOSX(x);
        CurioTiabClientConfig.setPOSY(y);

        CurioTiabClientConfig.save(
                CurioTiabClientConfig.Type.POSX,
                CurioTiabClientConfig.Type.POSY
        );

        CurioTiabClientConfig.invalidateCache(CurioTiabClientConfig.Type.POSX);
        CurioTiabClientConfig.invalidateCache(CurioTiabClientConfig.Type.POSY);
    }

    public void toggleOverlay() {
        HIDDEN.set(!HIDDEN.get());
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        if (HIDDEN.get())
            return;

        if (check) {
            this.x = CurioTiabClientConfig.getPosX();
            this.y = CurioTiabClientConfig.getPosY();
            check = !check;
        }

        AtomicInteger yOffset = new AtomicInteger(0);
        Player player = Minecraft.getInstance().player;
        Font font = Minecraft.getInstance().font;

        if (player != null) {
            ItemStack TIAB = Util.getTiabCurioItemStack(player);
            if (TIAB != ItemStack.EMPTY) {
                List<Component> Lines = new ArrayList<>();
                if (CurioTiabClientConfig.useHeader())
                    Lines.add(0, Component.literal("Time in a Bottle Curio").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));

                switch (CurioTiabClientConfig.getOverlayMode()) {
                    case TOTAL -> Lines.add(API.getTotalTimeTranslated(TIAB));
                    case CURRENT -> Lines.add(API.getStoredTimeTranslated(TIAB));
                    case BOTH -> {
                        Lines.add(API.getStoredTimeTranslated(TIAB));
                        Lines.add(API.getTotalTimeTranslated(TIAB));
                    }
                }

                Lines.forEach((component) ->
                        guiGraphics.drawString(font, component, this.x, this.y + yOffset.getAndAdd(10), 128));
            }
        }
    }
}