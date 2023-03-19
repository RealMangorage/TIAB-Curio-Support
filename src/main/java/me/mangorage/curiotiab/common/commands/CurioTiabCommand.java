package me.mangorage.curiotiab.common.commands;

import com.haoict.tiab.config.TiabConfig;
import com.haoict.tiab.items.TimeInABottleItem;
import com.haoict.tiab.utils.SendMessage;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.mangorage.curiotiab.common.core.Constants;
import me.mangorage.curiotiab.common.core.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;

public class CurioTiabCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> addTimeCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder) Commands.literal("addTime").requires((commandSource) -> {
        return commandSource.hasPermission(2);
    })).then(Commands.argument("seconds", MessageArgument.message()).executes((ctx) -> {
        try {
            return processTimeCommand(ctx, true);
        } catch (Exception var2) {
            var2.printStackTrace();
            return 0;
        }
    }));
    public static LiteralArgumentBuilder<CommandSourceStack> removeTimeCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("removeTime").requires((commandSource) -> {
        return commandSource.hasPermission(2);
    })).then(Commands.argument("seconds", MessageArgument.message()).executes((ctx) -> {
        try {
            return processTimeCommand(ctx, false);
        } catch (Exception var2) {
            var2.printStackTrace();
            return 0;
        }
    }));

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register((LiteralArgumentBuilder)((LiteralArgumentBuilder) Commands.literal("curiotiab").then(CurioTiabCommand.addTimeCommand)).then(CurioTiabCommand.removeTimeCommand));
    }

    private static int processTimeCommand(CommandContext<CommandSourceStack> ctx, boolean isAdd) throws CommandSyntaxException {
        Component messageValue = MessageArgument.getMessage(ctx, "seconds");
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();
        if (!messageValue.getString().isEmpty()) {
            try {
                int timeToAdd = Integer.parseInt(messageValue.getString());
                if (timeToAdd < 0) {
                    throw new NumberFormatException();
                }

                if (timeToAdd > TiabConfig.COMMON.maxStoredTime.get() / 20) {
                    timeToAdd = TiabConfig.COMMON.maxStoredTime.get() / 20;
                }

                boolean success = false;

                ItemStack tiabCurioItemStack = Util.getTiabCurioItemStack(player);
                if (tiabCurioItemStack != ItemStack.EMPTY) {
                    TimeInABottleItem item = (TimeInABottleItem) Constants.TIAB_ITEM;
                    int currentStoredEnergy = item.getStoredEnergy(tiabCurioItemStack);

                    if (!isAdd) {
                        if (currentStoredEnergy / 20 < timeToAdd) {
                            timeToAdd = currentStoredEnergy / 20;
                        }

                        timeToAdd = -timeToAdd;
                    }

                    item.setStoredEnergy(tiabCurioItemStack, currentStoredEnergy + timeToAdd * 20);
                    SendMessage.sendStatusMessage(player, String.format("%s %d seconds", isAdd ? "Added" : "Removed ", timeToAdd));
                    success = true;
                }

                if (!success) {
                    SendMessage.sendStatusMessage(player, "No Time in a bottle item in curio slot");
                }

                return 1;
            } catch (NumberFormatException var12) {
                SendMessage.sendStatusMessage(player, "Invalid time parameter! (is the number too big?)");
            }
        } else {
            SendMessage.sendStatusMessage(player, "Empty time parameter!");
        }

        return 0;
    }
}
