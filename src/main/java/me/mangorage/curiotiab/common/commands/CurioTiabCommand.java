package me.mangorage.curiotiab.common.commands;


import com.haoict.tiab.common.config.TiabConfig;
import com.haoict.tiab.common.items.TimeInABottleItem;
import com.magorage.tiab.api.ITimeInABottleAPI;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.mangorage.curiotiab.common.core.*;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Function;

import static me.mangorage.curiotiab.common.core.Constants.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CurioTiabCommand {
    private static final String ADD_TIME_COMMAND = "addTime";
    private static final String REMOVE_TIME_COMMAND = "removeTime";
    private static final String TIME_PARAM = "seconds";
    private static ITimeInABottleAPI API = null;
    private final static Function<ServerPlayer, ItemStack> GET_TIAB = (player) -> {
        var ret = Util.getTiabCurioItemStack(player);
        return ret == ItemStack.EMPTY ? null : ret;
    };

    public static void setAPI(ITimeInABottleAPI api) {
        if (API != null) return;
        API = api;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> addTimeCommand = Commands.literal(ADD_TIME_COMMAND).requires(commandSource -> commandSource.hasPermission(2)).then(Commands.argument(TIME_PARAM, MessageArgument.message()).executes((ctx) -> {
        try {
            return processTimeCommand(ctx, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }));
    public static LiteralArgumentBuilder<CommandSourceStack> removeTimeCommand = Commands.literal(REMOVE_TIME_COMMAND).requires(commandSource -> commandSource.hasPermission(2)).then(Commands.argument(TIME_PARAM, MessageArgument.message()).executes((ctx) -> {
        try {
            return processTimeCommand(ctx, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }));

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal(MODID).then(addTimeCommand).then(removeTimeCommand));
    }

    private static int processTimeCommand(CommandContext<CommandSourceStack> ctx, boolean isAdd) throws CommandSyntaxException {
        Component messageValue = MessageArgument.getMessage(ctx, TIME_PARAM);
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();
        return API.processCommand(GET_TIAB, player, messageValue, isAdd);
    }
}
