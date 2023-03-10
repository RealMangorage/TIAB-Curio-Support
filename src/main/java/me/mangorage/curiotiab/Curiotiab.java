package me.mangorage.curiotiab;

import me.mangorage.curiotiab.common.Util;
import me.mangorage.curiotiab.common.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
        Util.load();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueCompatMessages);
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

}
