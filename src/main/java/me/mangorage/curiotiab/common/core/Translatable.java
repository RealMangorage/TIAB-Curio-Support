package me.mangorage.curiotiab.common.core;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class Translatable {
    public final static Translatable
        MSG_ENABLED = new Translatable("msg.curiotiabgui.modify.enable"),
        MSG_DISABLED = new Translatable("msg.curiotiabgui.modify.disable"),
        MSG_RESET = new Translatable("msg.curiotiabgui.reset"),
        MSG_SAVED = new Translatable("msg.curiotiabgui.saved"),
        CATEGORY_KEY = new Translatable( "key.categories.curiotiab"),
        USE_KEY = new Translatable("key.curiotiab.use"),
        TOGGLE_KEY = new Translatable("key.curiotiab.hide"),
        SLOT_KEY = new Translatable("curios.identifier.tiab"),
        SCREEN_CONFIG = new Translatable("screen.curiotiab.config"),
        SCREEN_DRAG = new Translatable("screen.curiotiab.config.drag"),
        SCREEN_CLOSE = new Translatable("screen.curiotiab.config.close"),
        SCREEN_SAVE = new Translatable("screen.curiotiab.config.save"),
        SCREEN_RESET = new Translatable("screen.curiotiab.config.reset"),
        SCREEN_CLOSED = new Translatable("screen.curiotiab.config.closed");

    private final String key;

    public Translatable(String key) {
        this.key = key;
    }

    public MutableComponent get(String... args) {
        return Component.translatable(key, args);
    }

    public String getKey() {
        return key;
    }

}
