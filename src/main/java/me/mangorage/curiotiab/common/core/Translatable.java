package me.mangorage.curiotiab.common.core;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class Translatable {
    public final static Translatable
        MSG_RESET = new Translatable("msg.screen.config.curiotiab.reset"),
        MSG_SAVED = new Translatable("msg.screen.config.curiotiab.saved"),
        CATEGORY_KEY = new Translatable( "key.categories.curiotiab"),
        USE_KEY = new Translatable("key.curiotiab.use"),
        TOGGLE_KEY = new Translatable("key.curiotiab.hide"),
        SLOT_KEY = new Translatable("curios.identifier.tiab"),
        SCREEN_CONFIG = new Translatable("screen.curiotiab.config"),
        SCREEN_DRAG = new Translatable("screen.curiotiab.config.drag"),
        SCREEN_CLOSE = new Translatable("screen.curiotiab.config.close"),
        SCREEN_SAVE = new Translatable("screen.curiotiab.config.save"),
        SCREEN_RESET = new Translatable("screen.curiotiab.config.reset"),
        SCREEN_CLOSED = new Translatable("screen.curiotiab.config.closed"),
        COMMAND_RELOAD_CLIENT = new Translatable("msg.curiotiab.command.reload");

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


    public void sendSystemMessage(Player player, String... args) {
        player.sendSystemMessage(get(args));
    }

    public void sendSystemMessage(Player player, ChatFormatting[] formatting, String... args) {
        player.sendSystemMessage(get(args).withStyle(formatting));
    }

    public void sendSystemMessage(Player player, ChatFormatting formatting, String... args) {
        sendSystemMessage(player, new ChatFormatting[]{formatting}, args);
    }

}
