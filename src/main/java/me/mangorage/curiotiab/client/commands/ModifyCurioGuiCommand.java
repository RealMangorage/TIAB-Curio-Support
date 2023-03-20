package me.mangorage.curiotiab.client.commands;

import com.mojang.brigadier.context.CommandContext;
import me.mangorage.curiotiab.client.screens.ConfigurationScreen;
import me.mangorage.curiotiab.client.screens.overlays.CurioTiabHudOverlay;
import me.mangorage.curiotiab.common.core.Translatable;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;

import java.util.concurrent.atomic.AtomicBoolean;

public class ModifyCurioGuiCommand {
    public static int processCommand(CommandContext<CommandSourceStack> context) {
        ConfigurationScreen.getInstance().open();
        return 0;
    }
}
