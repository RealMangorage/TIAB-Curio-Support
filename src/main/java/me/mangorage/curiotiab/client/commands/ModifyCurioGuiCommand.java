package me.mangorage.curiotiab.client.commands;

import com.mojang.brigadier.context.CommandContext;
import me.mangorage.curiotiab.client.CurioTiabHudOverlay;
import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.concurrent.atomic.AtomicBoolean;

public class ModifyCurioGuiCommand {
    public final static String SAVE = "save";
    public final static String RESET = "reset";

    enum Type {
        RESET,
        SAVE,
        MODIFY
    }

    public static int saveCommand(CommandContext<CommandSourceStack> context) {
        return processCommand(context, Type.SAVE);
    }

    public static int resetCommand(CommandContext<CommandSourceStack> context) {
        return processCommand(context, Type.RESET);
    }

    public static int toggleCommand(CommandContext<CommandSourceStack> context) {
        return processCommand(context, Type.MODIFY);
    }


    private static int processCommand(CommandContext<CommandSourceStack> context, Type type) {
        switch (type) {
            case MODIFY -> {
                if (CurioTiabHudOverlay.getInstance().isHidden())
                    CurioTiabHudOverlay.getInstance().toggleOverlay();

                AtomicBoolean value = CurioTiabHudOverlay.getInstance().getMoving();
                if (value.get()) {
                    value.set(false);
                    context.getSource().sendSystemMessage(Translatable.MSG_DISABLED.get());
                } else {
                    value.set(true);
                    context.getSource().sendSystemMessage(Translatable.MSG_ENABLED.get());
                }
                return 0;
            }
            case RESET -> {
                context.getSource().sendSystemMessage(Translatable.MSG_RESET.get());
                CurioTiabHudOverlay.getInstance().reset();
                return 0;
            }
            case SAVE -> {
                context.getSource().sendSystemMessage(Translatable.MSG_SAVED.get());
                CurioTiabHudOverlay.getInstance().save();
                return 0;
            }
        }

        return 0;
    }
}
