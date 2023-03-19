package me.mangorage.curiotiab.common.core;

import net.minecraft.network.chat.Component;

public class Translatable {
    public final static Translatable
        MSG_ENABLED = new Translatable("msg.curiotiabgui.modify.enable"),
        MSG_DISABLED = new Translatable("msg.curiotiabgui.modify.disable"),
        MSG_RESET = new Translatable("msg.curiotiabgui.reset"),
        MSG_SAVED = new Translatable("msg.curiotiabgui.saved"),
        CATEGORY_KEY = new Translatable( "key.categories.curiotiab"),
        USE_KEY = new Translatable("key.curiotiab.use"),
        TOGGLE_KEY = new Translatable("key.curiotiab.hide"),
        SLOT_KEY = new Translatable("curios.identifier.tiab");

    private final String key;

    public Translatable(String key) {
        this.key = key;
    }

    public Component get(String... args) {
        return Component.translatable(key, args);
    }

    public String getKey() {
        return key;
    }

}
