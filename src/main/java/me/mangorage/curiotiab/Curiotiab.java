package me.mangorage.curiotiab;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.mangorage.curiotiab.common.commands.CurioTiabCommand;
import me.mangorage.curiotiab.common.network.NetworkHandler;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

import static me.mangorage.curiotiab.common.Constants.MODID;

@Mod(MODID)
public class Curiotiab {
    public Curiotiab() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueCompatMessages);
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        NetworkHandler.register();
    }
    public void enqueueCompatMessages(final InterModEnqueueEvent evt) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("tiab")
                        .size(1)
                        .icon(new ResourceLocation("curios:slot/empty_tiab"))
                        .build()
        );
    }
    public void onServerStarting(ServerStartingEvent event) {
        event.getServer().getCommands().getDispatcher().register((LiteralArgumentBuilder)((LiteralArgumentBuilder) Commands.literal("curiotiab").then(CurioTiabCommand.addTimeCommand)).then(CurioTiabCommand.removeTimeCommand));
    }
}
