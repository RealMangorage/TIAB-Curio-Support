package org.mangorage.tiabcurio.common.client.commands;


public class CurioTiabCommand {

    /**
    public static void register(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("curiotiab")
                .then(Commands.literal("configureOverlay")
                        .executes((stack) -> {
                            ConfigurationScreen.open(true);
                            return 1;
                        }))
                .then(Commands.literal("reloadconfig")
                        .executes(stack -> {
                            CurioTiabClientConfig.reload();
                            Translatable.COMMAND_RELOAD_CLIENT.sendSystemMessage(Minecraft.getInstance().player, ChatFormatting.GREEN);
                            return 1;
                        })));
    }
     **/
}
